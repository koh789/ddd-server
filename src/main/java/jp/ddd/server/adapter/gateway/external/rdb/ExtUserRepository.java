package jp.ddd.server.adapter.gateway.external.rdb;

import jp.ddd.server.external.mysql.custom.ExtUserRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface ExtUserRepository extends JpaRepository<ExtUser, Integer>, ExtUserRepositoryCtm {
}
