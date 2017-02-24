package jp.ddd.server.adapter.repository.mysql.entity;

import jp.ddd.server.adapter.repository.mysql.entity.base.BaseEntity;
import jp.ddd.server.adapter.repository.mysql.RoomUserRepository;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;
import org.eclipse.collections.api.set.ImmutableSet;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room_user")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "RoomUser.getWithDelByUnq", query = "SELECT r FROM RoomUser r WHERE r.roomId=:rid AND r.userId=:uid")
})
public class RoomUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_dt")
    private Date joinDt;

    @Column(name = "room_id")
    private Integer roomId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "user_id")
    private Integer userId;

    public static RoomUser create(Integer roomId, Integer userId, Date joinDt) {
        return RoomUser.builder().status(Status.VALID).joinDt(joinDt).roomId(roomId).userId(userId)
          .build();
    }

    public static ImmutableSet<RoomUser> register(RoomUserRepository rep, Integer roomId, ImmutableSet<Integer> userIds) {
        val joinDt = Dates.now();
        return userIds.collect(uid -> RoomUser.create(roomId, uid, joinDt)).collect(entity -> rep.save(entity));
    }

    public static ImmutableSet<RoomUser> update(RoomUserRepository rep, Integer roomId, ImmutableSet<Integer> userIds) {
        val joinDt = Dates.now();

        return userIds.collect(uid -> RoomUser.create(roomId, uid, joinDt)).collect(entity -> rep.save(entity));
    }
}