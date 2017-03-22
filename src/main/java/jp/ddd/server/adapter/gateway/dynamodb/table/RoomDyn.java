package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.DateDynamoDbConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "room")
public class RoomDyn implements BaseDyn {
    private static final long serialVersionUID = -4244111566039113744L;
    public static final String TABLE_NAME = "room";

    @DynamoDBHashKey(attributeName = "room_id")
    private Integer roomId;
    @DynamoDBAttribute(attributeName = "create_user_id")
    private String createUserId;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute(attributeName = "last_message_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastMessageAt;

}
