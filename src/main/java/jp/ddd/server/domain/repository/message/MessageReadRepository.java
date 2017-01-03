package jp.ddd.server.domain.repository.message;

import jp.ddd.server.domain.entity.MessageRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageReadRepository extends JpaRepository<MessageRead, Integer>, MessageReadRepositoryCtm {

}
