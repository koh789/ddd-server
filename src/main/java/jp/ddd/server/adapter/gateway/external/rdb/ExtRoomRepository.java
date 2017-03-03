package jp.ddd.server.adapter.gateway.external.rdb;

import jp.ddd.server.external.mysql.custom.ExtRoomRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface ExtRoomRepository extends JpaRepository<ExtRoom, Integer>, ExtRoomRepositoryCtm {
}
