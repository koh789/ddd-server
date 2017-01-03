package jp.ddd.server.web.form.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.ddd.server.utils.Msg;
import jp.ddd.server.utils.Regex;
import jp.ddd.server.web.form.Form;
import lombok.Data;

/**
 * ログインフォーム
 *
 * @author noguchi_kohei
 */
@Data
public class LoginForm implements Form {
  private static final long serialVersionUID = 1L;

  @NotNull(message = Msg.NOT_NULL)
  @Pattern(regexp = Regex.EMAIL_AND_TEL, message = Msg.ONLY_TEL_OR_EMAIL)
  private String loginId;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
  @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
  private String password;

}
