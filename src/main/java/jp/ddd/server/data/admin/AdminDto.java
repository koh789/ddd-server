package jp.ddd.server.data.admin;

import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.data.BaseVisitor;

/**
 * 管理者を表現します。
 *
 * @author noguchi_kohei
 */
public class AdminDto extends BaseVisitor {
  private static final long serialVersionUID = 1L;

  private Integer id;

  private String adminId;

  private String name;

  private AdminRole adminRole;

  private String eMail;

  private String note;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAdminid() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AdminRole getAdminRole() {
    return adminRole;
  }

  public void setAdminRole(AdminRole adminRole) {
    this.adminRole = adminRole;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
