package jp.ddd.server.adapter.gateway.rds.custom;

import jp.ddd.server.adapter.gateway.rds.entity.RoomExt;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRdsCtm {

    ImmutableList<RoomExt> findByDtDesc(Integer userId);

    Optional<RoomExt> getOpt(Integer id);

}
