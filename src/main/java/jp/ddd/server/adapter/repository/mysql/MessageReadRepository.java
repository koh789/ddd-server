package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.MessageRead;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageReadRepository extends JpaRepository<MessageRead, Long>, MessageReadRepositoryCtm {
}
