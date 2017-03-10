package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface RoomUserRds
  extends JpaRepository<RoomUserExt, Integer>, jp.ddd.server.adapter.gateway.rds.custom.RoomUserRdsCtm {
}
