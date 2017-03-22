package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;

/**
 * Created by noguchi_kohei 
 */
public interface UserDynGatewayCtm {


    UserDyn saveWithIncrementKey(UserDyn userDyn);
}
