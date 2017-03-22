package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.DateDynamoDbConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "message")
public class MessageDyn implements BaseDyn {
    private static final long serialVersionUID = 8630209538476323532L;

    public static final String TABLE_NAME = "message";

    @DynamoDBHashKey(attributeName = "message_id")
    private Integer messageId;

    @DynamoDBAttribute(attributeName = "room_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_rid_mat")
    private Integer roomId;

    @DynamoDBAttribute(attributeName = "message_at")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "idx_rid_mat")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date messageAt;

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    private String content;

    @DynamoDBAttribute(attributeName = "last_edit_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastEditAt;

    @DynamoDBAttribute(attributeName = "user_read_map")
    private Map<String, Boolean> userReadMap;

}
