package jp.ddd.server.domain.repository.admin;

import java.util.Optional;

import jp.ddd.server.domain.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

  Optional<Administrator> findByAdminIdAndDeleted(String adminId, Byte deleted);

  Optional<Administrator> findByAdminId(String adminId);

}
