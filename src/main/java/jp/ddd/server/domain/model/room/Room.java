package jp.ddd.server.domain.model.room;

import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The persistent class for the room database table.
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id AND r.deleted = :deleted"),
  @NamedQuery(name = "Room.findByUserIdOrderByMsgDateDesc", query = "SELECT r FROM Room r WHERE r.userId = :userId AND r.deleted = :deleted ORDER BY r.lastMessageDate DESC") })
public class Room extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private byte deleted;

    @Column(name = "last_message_dt")
    @Embedded
    private LastMessageDt lastMessageDt;

    private String name;

    @Column(name = "user_id")
    private int userId;

}