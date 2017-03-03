package jp.ddd.server.adapter.gateway.impl;

import jp.ddd.server.adapter.gateway.external.rdb.ExtRoomRepository;
import jp.ddd.server.adapter.gateway.external.rdb.ExtRoomUserRepository;
import jp.ddd.server.domain.model.room.Room;
import jp.ddd.server.domain.model.room.RoomUser;
import jp.ddd.server.domain.model.room.core.LastMessageDt;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.external.mysql.entity.ExtRoom;
import jp.ddd.server.external.mysql.entity.ExtRoomUser;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * {@link RoomRepositoryImpl}実装クラスです。
 * 当クラスでドメインモデルと外部プロトコル上のオブジェクトとの
 * インピーダンスミスマッチの解決を集約します。
 * Created by noguchi_kohei
 */
@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private ExtRoomRepository extRoomRepository;
    @Autowired
    private ExtRoomUserRepository extRoomUserRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Room register(UserId userId, String roomName, ImmutableList<UserId> joinUserIds) {
        val now = Dates.now();
        val extRoom = extRoomRepository.save(ExtRoom.create(userId.getId(), roomName, now));

        val extRoomUsers = joinUserIds //
          .distinct().collect(uid -> ExtRoomUser.create(extRoom.getId(), uid.getId(), now))
          .collect(entity -> extRoomUserRepository.save(entity));

        return Room.create(extRoom, extRoomUsers);
    }

    @Override
    public Optional<Room> getOpt(RoomId roomId) {
        return extRoomRepository.getOpt(roomId.getId())
          .map(r -> Room.create(r, extRoomUserRepository.findByRoomId(r.getId())));
    }

    @Override
    public void updateLastMsgDt(RoomId roomId, LastMessageDt lastMessageDt) {
        ExtRoom extRoom = extRoomRepository.getOpt(roomId.getId()) //
          .orElseThrow(() -> new NotFoundException("対象roomが存在しません" + roomId.getId()));
        extRoom.setLastMessageDt(lastMessageDt.getDate());
        extRoomRepository.save(extRoom);
    }

    @Override
    public ImmutableList<RoomUser> findRoomUser(RoomId roomId) {
        return extRoomUserRepository.findByRoomId(roomId.getId()).collect(eru -> RoomUser.create(eru));
    }
}
