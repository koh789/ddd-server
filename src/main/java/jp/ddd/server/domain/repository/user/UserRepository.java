package jp.ddd.server.domain.repository.user;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.ddd.server.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable>, UserRepositoryCtm {

  Optional<User> findByLoginIdAndDeleted(String loginId, byte deleted);

  Optional<User> findByIdAndDeleted(@Param("id") Integer userId, @Param("deleted") byte deleted);
}
