package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>, RoomUserRepositoryCtm {
}
