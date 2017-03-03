package jp.ddd.server.external.mysql.custom;

import jp.ddd.server.external.mysql.entity.ExtUser;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface ExtUserRepositoryCtm {

    Optional<ExtUser> getOpt(String loginId);

    Optional<ExtUser> getOpt(String loginId, String hashedPass);

    ImmutableList<ExtUser> find(ImmutableList<Integer> userIds);
}
