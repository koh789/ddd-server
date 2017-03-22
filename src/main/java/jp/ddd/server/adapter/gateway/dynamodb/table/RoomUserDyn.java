package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.DateDynamoDbConverter;
import jp.ddd.server.adapter.gateway.dynamodb.table.id.RoomUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "room_user")
public class RoomUserDyn implements BaseDyn {
    private static final long serialVersionUID = 5215040292663232116L;
    public static final String TABLE_NAME = "room_user";

    @Id
    @DynamoDBIgnore
    private RoomUserId roomUserId = new RoomUserId();

    @DynamoDBHashKey(attributeName = "room_id")
    public Integer getRoomId() {
        return this.roomUserId.getRoomId();
    }

    public void setRoomId(Integer roomId) {
        this.roomUserId.setRoomId(roomId);
    }

    @DynamoDBHashKey(attributeName = "user_id")
//    @DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_uid_lastat", attributeName = "user_id")
    public Integer getUserId() {
        return this.roomUserId.getUserId();
    }

    public void setUserId(Integer userId) {
        this.roomUserId.setUserId(userId);
    }

    /** yyyy-MM-dd HH:mm:ss */
    @DynamoDBAttribute(attributeName = "last_room_message_at")
//    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "idx_uid_lastat", attributeName = "last_room_message_at")
    private String lastRoomMessageAt;

    /** yyyy-MM-dd HH:mm:ss */
    @DynamoDBAttribute(attributeName = "join_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date joinAt;

}
