package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.usecase.gateway.rds.RoomRds;
import jp.ddd.server.usecase.gateway.rds.RoomUserRds;
import jp.ddd.server.domain.entity.room.Room;
import jp.ddd.server.domain.entity.room.RoomUser;
import jp.ddd.server.domain.entity.room.core.LastMessageDt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.RoomExt;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.domain.repository.RoomRepository;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * {@link RoomRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private RoomRds roomRds;
    @Autowired
    private RoomUserRds roomUserRds;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Room register(UserId userId, String roomName, ImmutableList<UserId> joinUserIds) {
        val now = Dates.now();
        val extRoom = roomRds.save(RoomExt.create(userId.getId(), roomName, now));

        val extRoomUsers = joinUserIds //
          .distinct().collect(uid -> RoomUserExt.create(extRoom.getId(), uid.getId(), now))
          .collect(entity -> roomUserRds.save(entity));

        return Room.create(extRoom, extRoomUsers);
    }

    @Override
    public Optional<Room> getOpt(RoomId roomId) {
        return roomRds.getOpt(roomId.getId()).map(r -> Room.create(r, roomUserRds.findByRoomId(r.getId())));
    }

    @Override
    public void updateLastMsgDt(RoomId roomId, LastMessageDt lastMessageDt) {
        RoomExt roomExt = roomRds.getOpt(roomId.getId()) //
          .orElseThrow(() -> new NotFoundException("対象roomが存在しません" + roomId.getId()));
        roomExt.setLastMessageDt(lastMessageDt.getDate());
        roomRds.save(roomExt);
    }

    @Override
    public ImmutableList<RoomUser> findRoomUser(RoomId roomId) {
        return roomUserRds.findByRoomId(roomId.getId()).collect(eru -> RoomUser.create(eru));
    }
}
