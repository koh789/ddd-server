package jp.ddd.server.domain.entity;

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
 * The persistent class for the message_read database table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message_read")
@NamedQueries({// 
  @NamedQuery(name = "MessageRead.findAll", query = "SELECT m FROM MessageRead m"), //
  @NamedQuery(//
    name = "MessageRead.findByMsgId",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId = :messageId AND m.deleted = :deleted ORDER BY m.id DESC"),
  @NamedQuery(//
    name = "MessageRead.findByMsgListAndUserId",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId in (:messageIdList) AND m.userId = :userId AND m.deleted in (:deletedList) ORDER BY m.id DESC"),
  @NamedQuery(//
    name = "MessageRead.findByUnique",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId = :messageId AND m.userId = :userId AND m.deleted in (:deletedList) ORDER BY m.id DESC")})

public class MessageRead extends BaseEntity {
  private static final long serialVersionUID = 2377932200497420692L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "message_id")
  private Long messageId;

  @Column(name = "user_id")
  private int userId;
}