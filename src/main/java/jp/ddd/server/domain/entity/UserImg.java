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
 * The persistent class for the user_img database table.
 */
@Data
@Entity
@Table(name = "user_img")
@NamedQuery(name = "UserImg.findAll", query = "SELECT u FROM UserImg u")
public class UserImg extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "image_type")
  private byte imageType;

  private String path;

  @Column(name = "user_id")
  private int userId;

}