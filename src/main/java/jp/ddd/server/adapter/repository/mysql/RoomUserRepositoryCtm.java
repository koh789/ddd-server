package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.RoomUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRepositoryCtm {

    RoomUser updateByUnq(Integer roomId, Integer userId);

    Optional<RoomUser> getOptByUnq(Integer roomId, Integer userId);
}
