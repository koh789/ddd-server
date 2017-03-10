package jp.ddd.server.domain.entity.room;

import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.room.core.JoinDt;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
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

    public static RoomUser create(RoomUserExt roomUserExt) {
        return RoomUser.builder() //
          .roomId(new RoomId(roomUserExt.getRoomId())) //
          .userId(new UserId(roomUserExt.getUserId()))//
          .joinDt(new JoinDt(roomUserExt.getJoinDt()))//
          .status(roomUserExt.getStatus()).build();
    }
}