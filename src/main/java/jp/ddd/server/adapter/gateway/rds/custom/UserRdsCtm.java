package jp.ddd.server.adapter.gateway.rds.custom;

import jp.ddd.server.adapter.gateway.rds.entity.UserExt;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserRdsCtm {

    Optional<UserExt> getOpt(String loginId);

    Optional<UserExt> getOpt(String loginId, String hashedPass);

    ImmutableList<UserExt> find(ImmutableList<Integer> userIds);
}
