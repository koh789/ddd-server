package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "ds_room_user")
public class RoomUserDyn implements Serializable {
    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey(attributeName = "user_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_uid_lastat")
    private String userId;
    @DynamoDBAttribute(attributeName = "room_id")
    private String roomId;
    @DynamoDBAttribute(attributeName = "last_room_message_at")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "idx_uid_lastat")
    private String lastRoomMessageAt;
    @DynamoDBAttribute(attributeName = "join_at")
    private String joinAt;
}
