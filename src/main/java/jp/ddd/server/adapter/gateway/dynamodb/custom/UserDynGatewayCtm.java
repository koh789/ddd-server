package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserDynGatewayCtm {


    UserDyn saveWithIncrementKey(UserDyn userDyn);

    Optional<UserDyn> getOptByLoginId(String loginId);

//    ImmutableList<UserDyn> find(ImmutableList<Integer> userIds);
}
