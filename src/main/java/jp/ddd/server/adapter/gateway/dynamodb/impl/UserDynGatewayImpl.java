package jp.ddd.server.adapter.gateway.dynamodb.impl;

import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class UserDynGatewayImpl implements UserDynGatewayCtm {
    @Autowired
    private DynamoDbClient<UserDyn> dynamoDbClient;

    @Override
    public UserDyn saveWithIncrementKey(UserDyn userDyn) {
        val id = dynamoDbClient.incrementNum(UserDyn.TABLE_NAME);
        userDyn.setUserId(id);
        return dynamoDbClient.save(userDyn);
    }
}
