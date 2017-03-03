package jp.ddd.server.adapter.gateway.external.rdb;

import jp.ddd.server.external.mysql.custom.ExtMessageReadRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtMessageRead;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface ExtMessageReadRepository extends JpaRepository<ExtMessageRead, Long>, ExtMessageReadRepositoryCtm {
}
