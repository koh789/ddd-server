package jp.ddd.server.adapter.gateway.dynamodb.impl;

import jp.ddd.server.adapter.gateway.dynamodb.custom.RoomDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class RoomDynGatewayImpl implements RoomDynGatewayCtm{
    @Autowired
    private DynamoDbClient<RoomDyn> dynamoDbClient;

    @Override
    public RoomDyn saveWithIncrementKey(RoomDyn roomDyn){
        val id = dynamoDbClient.incrementNum(RoomDyn.TABLE_NAME);
        roomDyn.setRoomId(id);
        return dynamoDbClient.save(roomDyn);
    }
}
