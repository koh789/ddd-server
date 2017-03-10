package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.entity.MessageExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRds
  extends JpaRepository<MessageExt, Long>, jp.ddd.server.adapter.gateway.rds.custom.MessageRdsCtm {
}
