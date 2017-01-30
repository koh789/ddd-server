package jp.ddd.server.domain.model.message;

import jp.ddd.server.domain.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the message_read database table.
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message_read")
@NamedQueries({// 
  @NamedQuery(//
    name = "MessageRead.findByMsgId",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId = :mdi AND m.deleted = 0 ORDER BY m.id DESC"),
  @NamedQuery(//
    name = "MessageRead.findByMsgListAndUserId",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId in (:mids) AND m.userId = :uid AND m.deleted in (:dels) ORDER BY m.id DESC"),
  @NamedQuery(//
    name = "MessageRead.findByUnique",//
    query = "SELECT m FROM MessageRead m WHERE m.messageId = :mid AND m.userId = :uid AND m.deleted in (:dels) ORDER BY m.id DESC") })

public class MessageRead extends BaseEntity {
    private static final long serialVersionUID = 2377932200497420692L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "deleted")
    private byte deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_dt")
    private Date readDt;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id")
    private int userId;
}