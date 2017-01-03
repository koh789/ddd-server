package jp.ddd.server.domain.repository.message;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import jp.ddd.server.domain.entity.MessageRead;
import org.eclipse.collections.api.list.ImmutableList;

public interface MessageReadRepositoryCtm {

  /**
   * メッセージに紐づく既読stmを取得します。
   */
  Stream<MessageRead> findStm(Long messageId);

  ImmutableList<MessageRead> find(Long messageId);

  Stream<MessageRead> findStmByUnique(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted);

  ImmutableList<MessageRead> findByUnique(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted);

  Optional<MessageRead> findOptByUnique(Long messageId, Integer readUserId, boolean isContainDeleted);

  MessageRead saveByUnique(Long messageId, Integer readUserId, boolean isContainDeleted);

  Stream<MessageRead> saveByUnique(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted);

  ImmutableList<MessageRead> saveByUniques(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted);

  void logicalDelete(Long messageId);

}
