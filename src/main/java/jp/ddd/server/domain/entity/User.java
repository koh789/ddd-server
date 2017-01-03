package jp.ddd.server.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jp.ddd.server.domain.entity.base.BaseEntity;
import lombok.Data;

/**
 * The persistent class for the user database table.
 */

@Data
@Entity
@NamedQueries({// 
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),//
  @NamedQuery(//
    name = "User.findByIdList",//
    query = "SELECT u FROM User u WHERE u.id in (:idList) AND u.deleted = :deleted")//
})
public class User extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Lob
  private String discription;

  @Column(name = "e_mail")
  private String eMail;

  @Column(name = "login_id")
  private String loginId;

  @Column(name = "login_id_type")
  private byte loginIdType;

  private String name;

  private String password;

  private String tel;

}