package jp.ddd.server.domain.repository.room;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import jp.ddd.server.domain.entity.Room;
import org.eclipse.collections.api.list.ImmutableList;

public interface RoomRepositoryCtm {

  Optional<Room> findOpt(Integer id);

  Stream<Room> findStm(Integer userId);

  ImmutableList<Room> find(Integer userId);

  /**
   * 指定ルームのメッセージ数を1減算します。lastMessageId,lastMessageDateがある場合そちらも更新します。
   */
  Optional<Room> countDown(Integer id, Long lastMessageId, Date lastMessageDate);

  Optional<Room> countDown(Integer id);
}
