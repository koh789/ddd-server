package jp.ddd.server.domain.entity.room;

import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.room.core.LastMessageDt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.RoomExt;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

@Builder
@AllArgsConstructor
@Value
public class Room implements Entity<Room> {
    private static final long serialVersionUID = -5335233503985740063L;

    private final RoomId roomId;

    private final LastMessageDt lastMessageDt;

    private final String name;

    private final UserId userId;

    private final Status status;

    private final ImmutableList<RoomUser> roomUsers;

    public static Room create(RoomExt roomExt, ImmutableList<RoomUserExt> extRoomUsers) {

        ImmutableList<RoomUser> roomUsers = extRoomUsers.collect(eru -> RoomUser.create(eru));

        return Room.builder()//
          .roomId(new RoomId(roomExt.getId()))//
          .lastMessageDt(new LastMessageDt(roomExt.getLastMessageDt()))//
          .name(roomExt.getName())//
          .userId(new UserId(roomExt.getUserId()))//
          .status(roomExt.getStatus())//
          .roomUsers(roomUsers).build();
    }
}