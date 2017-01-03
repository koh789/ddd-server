package jp.ddd.server.web.form.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jp.ddd.server.utils.Regex;
import jp.ddd.server.web.form.BaseForm;

/**
 * ログインフォーム
 *
 * @author noguchi_kohei
 */
public class LoginForm extends BaseForm {
  private static final long serialVersionUID = 1L;

  @NotNull(message = "入力必須です。")
  @Pattern(regexp = Regex.PASSWORD, message = "半角英数字のみです。")
  private String adminId;

  @NotNull(message = "半角英数字のみです。")
  @Pattern(regexp = Regex.PASSWORD, message = "半角英数字のみです。")
  private String password;

  public String getAdminId() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
