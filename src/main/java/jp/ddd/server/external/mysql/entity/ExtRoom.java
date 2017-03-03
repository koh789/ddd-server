package jp.ddd.server.external.mysql.entity;

import jp.ddd.server.adapter.gateway.external.rdb.ExtRoomRepository;
import jp.ddd.server.adapter.gateway.external.rdb.ExtRoomUserRepository;
import jp.ddd.server.external.mysql.entity.base.BaseEntity;
import jp.ddd.server.other.exception.NotFoundException;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room")
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Room.findWithRoomUserByUidDtStatusDesc",//
    query = "SELECT r FROM Room r JOIN FETCH r.roomUsers ru WHERE r.userId=:uid AND r.status=:status AND ru.status=:status ORDER BY r.lastMessageDt DESC"),
  @NamedQuery(name = "Room.getOptWithRoomUserByRidStatus", //
    query = "SELECT r FROM Room r JOIN FETCH r.roomUsers ru WHERE r.id=:rid AND r.status=:status AND ru.status=:status") })
public class ExtRoom extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_message_dt")
    private Date lastMessageDt;

    private String name;

    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<ExtRoomUser> roomUsers;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<ExtMessage> messages;

    public static ExtRoom create(Integer userId, String roomName, Date lastMessageDt) {
        return ExtRoom.builder().status(Status.VALID).lastMessageDt(lastMessageDt).name(roomName).userId(userId)
          .build();
    }

    /**
     * メッセージルームを新規作成します。
     * @param userId
     * @param roomName
     * @param joinUserIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public static ExtRoom registerWithRoomUser(ExtRoomRepository roomRepository,
      ExtRoomUserRepository roomUserRepository, //
      Integer userId, String roomName, ImmutableList<Integer> joinUserIds) {

        val entity = ExtRoom.create(userId, roomName, Dates.now());
        val result = roomRepository.save(entity);
        val userIds = joinUserIds.newWith(userId).toSet().toImmutable();
        ExtRoomUser.register(roomUserRepository, result.getId(), userIds);
        return result;
    }

    public static Optional<ExtRoom> getOpt(Integer roomId, ExtRoomRepository roomRepository) {
        return Optional.ofNullable(roomRepository.getOne(roomId));
    }

    /**
     * 最終メッセージ日時を更新します。
     * @param rep
     * @param roomId
     * @param dt
     */
    public static void updateLastMsgDt(ExtRoomRepository rep, Integer roomId, Date dt) {
        ExtRoom entity = Optional.ofNullable(rep.getOne(roomId)).orElseThrow(() -> new NotFoundException());
        entity.setLastMessageDt(dt);
        rep.save(entity);
    }

    /**
     * 対象ルームからユーザを取得します。
     * @param roomId
     * @param rep
     * @return
     */
    public static ImmutableList<ExtRoomUser> findRoomUser(Integer roomId, ExtRoomRepository rep) {
        val room = rep.getOpt(roomId).orElseThrow(() -> new NotFoundException("対象のメッセージルームが存在しません。" + roomId));
        return DsLists.toImt(room.getRoomUsers());
    }

    /**
     * 対象ルームにユーザを追加します。
     * @param roomId
     * @param addUserIds
     * @param rep
     * @return
     */
    public static ImmutableList<ExtRoomUser> addRoomUser(Integer roomId, ImmutableList<Integer> addUserIds,
      ExtRoomUserRepository rep) {
        val roomUsers = addUserIds.collect(uid -> ExtRoomUser.create(roomId, uid, Dates.now()));
        return DsLists.toImt(rep.save(roomUsers.castToList()));
    }
}