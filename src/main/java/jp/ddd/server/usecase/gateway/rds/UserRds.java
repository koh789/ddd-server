package jp.ddd.server.usecase.gateway.rds;

import jp.ddd.server.adapter.gateway.rds.custom.UserRdsCtm;
import jp.ddd.server.adapter.gateway.rds.entity.UserExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface UserRds extends JpaRepository<UserExt, Integer>, UserRdsCtm {
}
