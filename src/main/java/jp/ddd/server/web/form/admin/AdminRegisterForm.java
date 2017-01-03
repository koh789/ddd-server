package jp.ddd.server.web.form.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.ddd.server.utils.enums.AdminRole;
import org.hibernate.validator.constraints.Email;

import jp.ddd.server.utils.Msg;
import jp.ddd.server.utils.Regex;
import jp.ddd.server.web.form.Form;
import lombok.Data;

/**
 * 管理者登録フォーム
 * validatorは後でつくる
 *
 * @author noguchi_kohei
 */
@Data
public class AdminRegisterForm implements Form {
  private static final long serialVersionUID = 1L;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 5, max = 30, message = Msg.INPUT_5TO30)
  @Pattern(regexp = Regex.PASSWORD)
  private String adminId;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
  @Pattern(regexp = Regex.PASSWORD)
  private String password;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
  @Pattern(regexp = Regex.PASSWORD)
  private String confirmPassword;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 3, max = 30, message = Msg.INPUT_3TO30)
  private String name;

  /**
   * {@link AdminRole}
   */
  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 1, max = 2, message = Msg.INPUT_INVALID)
  private Byte roleType;

  @Email
  @Size(min = 0, max = 50, message = Msg.INPUT_TO50)
  private String eMail;

  @Size(min = 0, max = 200, message = Msg.INPUT_TO200)
  private String note;
}
