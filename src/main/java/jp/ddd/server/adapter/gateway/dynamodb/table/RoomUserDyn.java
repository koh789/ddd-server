package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "ds_room_user")
public class RoomUserDyn implements Serializable{
    private static final long serialVersionUID = 1L;

}
