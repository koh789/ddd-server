package jp.ddd.server.adapter.gateway.dynamodb.impl;

import jp.ddd.server.adapter.gateway.dynamodb.custom.SequenceDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.SequenceDyn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class SequenceDynGatewayImpl implements SequenceDynGatewayCtm {
    @Autowired
    private DynamoDbClient<SequenceDyn> dynamoDbClient;

    @Override
    public Integer incrementNum(String tableName) {
        return dynamoDbClient.incrementNum(tableName);
    }
}
