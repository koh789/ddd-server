package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.Room;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRepositoryCtm {

    ImmutableList<Room> findByDtDesc(Integer userId);

    Optional<Room> getOpt(Integer id);
}
