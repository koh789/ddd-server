package jp.ddd.server.domain.repository.room;

import java.util.stream.Stream;

import jp.ddd.server.domain.entity.RoomUser;
import org.eclipse.collections.api.list.ImmutableList;

public interface RoomUserRepositoryCtm {

  Stream<RoomUser> findStm(Integer roomId);

  ImmutableList<RoomUser> find(Integer roomId);

  Stream<Integer> findUserIdStm(Integer roomId);

  ImmutableList<Integer> findUserId(Integer roomId);
}
