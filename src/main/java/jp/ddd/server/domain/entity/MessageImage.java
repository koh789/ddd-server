package jp.ddd.server.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.Data;

/**
 * The persistent class for the message_image database table.
 */
@Data
@Entity
@Table(name = "message_image")
@NamedQuery(name = "MessageImage.findAll", query = "SELECT m FROM MessageImage m")
public class MessageImage extends BaseEntity {
  private static final long serialVersionUID = -8868079370265289754L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "message_id")
  private int messageId;

  private String path;

}