package jp.ddd.server.external.mysql.entity;

import jp.ddd.server.adapter.gateway.external.rdb.ExtMessageReadRepository;
import jp.ddd.server.adapter.gateway.external.rdb.ExtMessageRepository;
import jp.ddd.server.external.mysql.entity.base.BaseEntity;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.data.message.RegisterMessageParam;
import jp.ddd.server.other.utils.enums.Status;
import lombok.*;
import org.eclipse.collections.api.list.ImmutableList;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the message database table.
 */
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Message.findWithReadByRoomIdOrderByIdDesc", //
    query = "SELECT m FROM Message m JOIN FETCH  m.messageReads mr WHERE m.roomId=:rid AND m.deleted=0 ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findUnreadByRidAndUid",//
    query = "SELECT m FROM Message m LEFT JOIN FETCH m.messageReads mr WHERE m.roomId=:rid AND m.userId<>:uid AND (mr.id IS NULL OR mr.userId <> :uid) AND m.deleted=0") })
public class ExtMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "last_edit_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditDt;

    @Column(name = "message_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDt;

    @Column(name = "room_id")
    private Integer roomId;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany
    @JoinColumn(name = "message_id")
    private List<ExtMessageRead> messageReads;

    public static ExtMessage create(RegisterMessageParam param) {
        return ExtMessage.builder().content(param.getContent()).status(Status.VALID).lastEditDt(param.getMessageDt())
          .messageDt(param.getMessageDt()).roomId(param.getRoomId()).userId(param.getUserId()).build();
    }

    /**
     * メッセージを新規登録します。
     * @param param
     * @param rep
     * @return
     */
    public static ExtMessage register(RegisterMessageParam param, ExtMessageRepository rep) {

        return rep.save(ExtMessage.create(param));
    }

    /**
     * 対象roomのメッセージリストを読み込みます。
     * 同時に対象メッセージに対して既読情報を更新します。
     * @param roomId
     * @param userId
     * @return
     */
    public static ImmutableList<ExtMessage> loadAndSaveRead(Integer roomId, Integer userId, Page page,
      ExtMessageRepository msgRep, ExtMessageReadRepository readRep) {

        findUnread(roomId, userId, msgRep).each(m -> readRep.save(ExtMessageRead.create(m.getId(), userId)));
        return msgRep.findByRoomId(roomId, page);
    }

    /**
     * 指定ユーザIDの指定メッセージIDリストの中から既読済みメッセージ情報を取得します。
     * @param userId
     * @param rep
     * @return
     */
    public static ImmutableList<ExtMessage> findUnread(Integer roomId, Integer userId, ExtMessageRepository rep) {
        return rep.findUnread(roomId, userId);
    }
}