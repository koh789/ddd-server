package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCtm {
}
