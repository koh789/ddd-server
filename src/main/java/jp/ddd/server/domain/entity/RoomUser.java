package jp.ddd.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the room_user database table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room_user")
@NamedQueries({// 
  @NamedQuery(name = "RoomUser.findAll", query = "SELECT r FROM RoomUser r"),
  @NamedQuery(name = "RoomUser.findByRoomId", query = "SELECT r FROM RoomUser r WHERE r.roomId = :roomId AND r.deleted = :deleted ORDER BY r.id DESC")})
public class RoomUser extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "room_id")
  private int roomId;

  @Column(name = "user_id")
  private int userId;

}