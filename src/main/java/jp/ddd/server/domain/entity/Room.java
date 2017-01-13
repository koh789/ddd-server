package jp.ddd.server.domain.entity;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The persistent class for the room database table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({ //
  @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
  @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id AND r.deleted = :deleted"),
  @NamedQuery(name = "Room.findByUserIdOrderByMsgDateDesc", query = "SELECT r FROM Room r WHERE r.userId = :userId AND r.deleted = :deleted ORDER BY r.lastMessageDate DESC") })
public class Room extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int count;

    private byte deleted;

    private String name;

    @Column(name = "user_id")
    private int userId;

}