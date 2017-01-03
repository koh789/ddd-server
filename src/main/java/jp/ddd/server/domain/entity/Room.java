package jp.ddd.server.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @NamedQuery(name = "Room.findByUserIdOrderByMsgDateDesc", query = "SELECT r FROM Room r WHERE r.userId = :userId AND r.deleted = :deleted ORDER BY r.lastMessageDate DESC")})
public class Room extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private int count;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_message_date")
  private Date lastMessageDate;

  @Column(name = "last_message_id")
  private Long lastMessageId;

  private String name;

  @Column(name = "user_id")
  private int userId;

}