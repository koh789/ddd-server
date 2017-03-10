package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.MessageReadExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageReadRds
  extends JpaRepository<MessageReadExt, Long>, jp.ddd.server.adapter.gateway.rds.custom.MessageReadRdsCtm {
}
