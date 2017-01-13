package jp.ddd.server.domain.entity;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the message database table.
 */
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "message_dt")
    private Date messageDt;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "user_id")
    private int userId;

}