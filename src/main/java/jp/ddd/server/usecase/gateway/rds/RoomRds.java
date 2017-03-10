package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.custom.RoomRdsCtm;
import jp.ddd.server.adapter.gateway.rds.entity.RoomExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomRds extends JpaRepository<RoomExt, Integer>, RoomRdsCtm {
}
