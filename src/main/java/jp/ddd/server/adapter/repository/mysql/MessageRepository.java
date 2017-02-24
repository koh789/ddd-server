package jp.ddd.server.adapter.repository.mysql;

import jp.ddd.server.adapter.repository.mysql.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCtm {
}
