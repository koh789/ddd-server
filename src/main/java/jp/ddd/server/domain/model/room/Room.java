package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.Entity;
import jp.ddd.server.domain.model.room.core.LastMessageDt;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.external.mysql.entity.ExtRoom;
import jp.ddd.server.external.mysql.entity.ExtRoomUser;
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

    public static Room create(ExtRoom extRoom, ImmutableList<ExtRoomUser> extRoomUsers) {

        ImmutableList<RoomUser> roomUsers = extRoomUsers.collect(eru -> RoomUser.create(eru));

        return Room.builder()//
          .roomId(new RoomId(extRoom.getId()))//
          .lastMessageDt(new LastMessageDt(extRoom.getLastMessageDt()))//
          .name(extRoom.getName())//
          .userId(new UserId(extRoom.getUserId()))//
          .status(extRoom.getStatus())//
          .roomUsers(roomUsers).build();
    }
}