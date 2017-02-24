package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRepository extends JpaRepository<Room, Integer>, RoomRepositoryCtm {
}
