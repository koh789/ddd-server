package jp.ddd.server.domain.repository.message;

import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.ImmutableList;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.domain.entity.Message;

public interface MessageRepositoryCtm {

  Optional<Message> findOpt(Long id);

  Stream<Message> findStmByRoomId(Integer roomId);

  ImmutableList<Message> findByRoomId(Integer roomId);

  Stream<Integer> findUserIdStm(Integer roomId);

  ImmutableList<Integer> findUserId(Integer roomId);

  Stream<Message> findStmByRoomId(Integer roomId, Optional<PageParam> pageOpt);

  ImmutableList<Message> findByRoomId(Integer roomId, Optional<PageParam> pageOpt);

  Stream<Message> findStmByUserId(Integer userId);

  ImmutableList<Message> findByUserId(Integer userId);

  void logicalDelete(Long id);
}
