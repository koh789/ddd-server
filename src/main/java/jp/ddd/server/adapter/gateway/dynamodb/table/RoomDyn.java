package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.converter.DateDynamoDbConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "room")
public class RoomDyn implements BaseDyn {
    private static final long serialVersionUID = -4244111566039113744L;

    @DynamoDBHashKey(attributeName = "room_id")
    private Integer roomId;
    @DynamoDBAttribute(attributeName = "create_user_id")
    private Integer createUserId;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute(attributeName = "last_message_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastMessageAt;

    public static RoomDyn create(Integer userId, String roomName, Date lastMessageDt) {
        return RoomDyn.builder().lastMessageAt(lastMessageDt).name(roomName).createUserId(userId).build();
    }

    public RoomDyn withRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }
}
