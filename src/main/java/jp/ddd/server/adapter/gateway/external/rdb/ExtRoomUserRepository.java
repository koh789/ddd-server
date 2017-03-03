package jp.ddd.server.adapter.gateway.external.rdb;

import jp.ddd.server.external.mysql.custom.ExtRoomUserRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface ExtRoomUserRepository extends JpaRepository<ExtRoomUser, Integer>, ExtRoomUserRepositoryCtm {
}
