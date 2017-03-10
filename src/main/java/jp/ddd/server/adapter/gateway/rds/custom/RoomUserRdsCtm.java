package jp.ddd.server.adapter.gateway.rds.custom;

import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRdsCtm {

    Optional<RoomUserExt> getOptByUnq(Integer roomId, Integer userId);

    ImmutableList<RoomUserExt> findByRoomId(Integer roomId);
}
