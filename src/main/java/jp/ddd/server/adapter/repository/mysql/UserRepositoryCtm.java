package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.User;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface UserRepositoryCtm {

    Optional<User> getOpt(String loginId);

    Optional<User> getOpt(String loginId, String hashedPass);

    ImmutableList<User> find(ImmutableList<Integer> userIds);
}
