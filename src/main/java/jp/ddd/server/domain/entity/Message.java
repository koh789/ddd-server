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
 * The persistent class for the message database table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@NamedQueries({@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
  @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id AND m.deleted = :deleted"),
  @NamedQuery(name = "Message.findByRoomId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findByRoomIdLessThanLastId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted AND m.id < :lastId ORDER BY m.id DESC"),
  @NamedQuery(name = "Message.findByUserId", query = "SELECT m FROM Message m WHERE m.roomId = :roomId AND m.deleted = :deleted ORDER BY m.id DESC")})
public class Message extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String content;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "message_date")
  private Date messageDate;

  @Column(name = "room_id")
  private int roomId;

  @Column(name = "user_id")
  private int userId;

}