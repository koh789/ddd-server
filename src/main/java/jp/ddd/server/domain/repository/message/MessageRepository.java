package jp.ddd.server.domain.repository.message;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ddd.server.domain.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>, MessageRepositoryCtm {

}