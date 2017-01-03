package jp.ddd.server.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import jp.ddd.server.domain.entity.base.BaseEntity;
import jp.ddd.server.utils.Dates;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.utils.enums.Deleted;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the administrator database table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a")
public class Administrator extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "admin_id")
  private String adminId;

  @Column(name = "e_mail")
  private String eMail;

  private String note;

  private String password;

  private byte role;

  @Column(name = "user_name")
  private String userName;

  public static Administrator create(String adminId, String userName, String email, String hashedPass, String note,
                                     AdminRole role) {
    Administrator entity = new Administrator();
    entity.setAdminId(adminId);
    entity.setUserName(userName);
    entity.setEMail(email);
    entity.setPassword(hashedPass);
    entity.setNote(note);
    entity.setRole(role.getCode());
    entity.setDeleted(Deleted.PUBLIC.getCode());
    entity.setCreateTime(Dates.now());
    entity.setUpdateTime(Dates.now());
    return entity;

  }
}