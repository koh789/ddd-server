package jp.ddd.server.adapter.gateway.dynamodb.impl.base;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jp.ddd.server.adapter.gateway.dynamodb.table.SequenceDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.other.exception.InternalException;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class DynamoDbClient<T extends BaseDyn> {
    @Value("${aws.dynamoDb.endpoint}")
    protected String dynamoDBEndpoint;
    @Value("${aws.dynamoDb.prefix}")
    protected String tableNamePrefix;
    @Value("${aws.accessKey}")
    protected String awsAccessKey;
    @Value("${aws.secretKey}")
    protected String awsSecretKey;

    protected AmazonDynamoDBClient getClient() {

        val credential = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(credential);
        client.setEndpoint(dynamoDBEndpoint);
        return client;
    }

    /**
     * DynamoDbMapperを取得します。
     * このmapperはスレッドセーフのため、スレッド間の共有も可能です。
     * @return
     */
    public DynamoDBMapper getMapper() {
        return new DynamoDBMapper(getClient());
    }

    /**
     * テーブルにプレフィックスを付与するためmapperのメソッドを使用するために使用してください。
     * @return
     */
    public DynamoDBMapperConfig getMapperConfig() {
        val config = DynamoDBMapperConfig.builder()
          .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(tableNamePrefix)).build();
        return config;
    }

    /**
     * sequenceテーブルで保持している指定テーブルのcurrent_numを更新後
     * インクリメントした値(Long型)を返却します。
     * @param tableName プレフィックスなしのテーブル名を指定してください。
     * @return
     */
    public Long incrementLongNum(String tableName) {
        val mapper = getMapper();
        val mapperConfig = getMapperConfig();
        val seqOpt = Optional.ofNullable(mapper.load(SequenceDyn.class, tableName, mapperConfig));
        return seqOpt.map(seq -> {
            val currentNum = seq.getCurrentNum() + 1L;
            mapper.save(SequenceDyn.create(seq.getName(), currentNum), mapperConfig);
            return currentNum;
        }).orElseGet(() -> {
            val currentNum = 1L;
            mapper.save(SequenceDyn.create(tableName, currentNum), mapperConfig);
            return currentNum;
        });
    }
    /**
     * sequenceテーブルで保持している指定テーブルのcurrent_numを
     * インクリメント後、その値を返却します。
     * 対応クラスに@DynamoDBTableが付与されていることを前提としています。
     * @param clazz
     * @return
     * @exception InternalException
     */
    public Integer incrementNum(Class<T> clazz) {
        val annotation = Optional.ofNullable(clazz.getDeclaredAnnotation(DynamoDBTable.class))
          .orElseThrow(() -> new InternalException("DynamoDBTableアノテーションが付与されていません。"));
        return incrementLongNum(annotation.tableName()).intValue();
    }

    public Long incrementLongNum(Class<T> clazz) {
        val annotation = Optional.ofNullable(clazz.getDeclaredAnnotation(DynamoDBTable.class))
          .orElseThrow(() -> new InternalException("DynamoDBTableアノテーションが付与されていません。"));
        return incrementLongNum(annotation.tableName());
    }

    public T save(T t) {
        getMapper().save(t, getMapperConfig());
        return t;
    }
}
