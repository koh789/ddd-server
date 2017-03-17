package jp.ddd.server.adapter.gateway.rds.custom;

import jp.ddd.server.adapter.gateway.rds.entity.UserRds;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserRdsGatewayCtm {

    Optional<UserRds> getOpt(String loginId);

    Optional<UserRds> getOpt(String loginId, String hashedPass);

    ImmutableList<UserRds> find(ImmutableList<Integer> userIds);
}
