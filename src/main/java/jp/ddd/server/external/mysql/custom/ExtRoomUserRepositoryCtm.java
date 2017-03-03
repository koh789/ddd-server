package jp.ddd.server.external.mysql.custom;

import jp.ddd.server.external.mysql.entity.ExtRoomUser;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface ExtRoomUserRepositoryCtm {

    Optional<ExtRoomUser> getOptByUnq(Integer roomId, Integer userId);

    ImmutableList<ExtRoomUser> findByRoomId(Integer roomId);
}
