package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.Entity;
import jp.ddd.server.domain.model.room.core.JoinDt;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.external.mysql.entity.ExtRoomUser;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class RoomUser implements Entity<RoomUser> {
    private static final long serialVersionUID = 3373581417552541323L;

    private final RoomId roomId;

    private final UserId userId;

    private final JoinDt joinDt;

    private final Status status;

    public static RoomUser create(ExtRoomUser extRoomUser) {
        return RoomUser.builder() //
          .roomId(new RoomId(extRoomUser.getRoomId())) //
          .userId(new UserId(extRoomUser.getUserId()))//
          .joinDt(new JoinDt(extRoomUser.getJoinDt()))//
          .status(extRoomUser.getStatus()).build();
    }
}