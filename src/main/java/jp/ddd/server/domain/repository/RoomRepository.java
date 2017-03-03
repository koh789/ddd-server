package jp.ddd.server.domain.repository;

import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.domain.model.room.RoomUser;
import jp.ddd.server.domain.model.room.core.LastMessageDt;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * ルームに関するレポジトリを表現します。
 * ルームは集約ルートエンティティのため、集約ルート内のオブジェクトに関しては
 * 全てこのレポジトリを使用して取得することを想定しています。
 * Created by noguchi_kohei
 */
public interface RoomRepository {

    Room register(UserId userId, String roomName, ImmutableList<UserId> joinUserIds);

    Optional<Room> getOpt(RoomId roomId);

    void updateLastMsgDt(RoomId roomId, LastMessageDt lastMessageDt);

    ImmutableList<RoomUser> findRoomUser(RoomId roomId);
}
