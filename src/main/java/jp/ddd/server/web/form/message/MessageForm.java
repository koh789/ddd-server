package jp.ddd.server.web.form.message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.form.BaseForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageForm extends BaseForm {

  private static final long serialVersionUID = 1L;

  @NotNull(message = Msg.NOT_NULL)
  private Integer userId;

  @NotNull(message = Msg.NOT_NULL)
  @Size(min = 0, max = 200, message = Msg.INPUT_TO200)
  private String content;
}
