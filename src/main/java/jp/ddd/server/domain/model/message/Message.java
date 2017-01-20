package jp.ddd.server.domain.model.message;

import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The persistent class for the message database table.
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({
  @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id AND m.deleted = :deleted"),
  @NamedQuery(name = "Message.findByRoomId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findByRoomIdLessThanLastId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted AND m.id < :lastId ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findByUserId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted ORDER BY m.id DESC") })
public class Message extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    @Column(name = "deleted")
    private byte deleted;


    @Column(name = "message_dt")
    @Embedded
    private MessageDt messageDt;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "user_id")
    private int userId;

}