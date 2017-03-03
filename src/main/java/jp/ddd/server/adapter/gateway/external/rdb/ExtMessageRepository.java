package jp.ddd.server.adapter.gateway.external.rdb;

import jp.ddd.server.external.mysql.custom.ExtMessageRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface ExtMessageRepository extends JpaRepository<ExtMessage, Long>, ExtMessageRepositoryCtm {
}
