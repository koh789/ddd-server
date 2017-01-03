package jp.ddd.server.web.form.room;

import java.util.List;

import javax.validation.constraints.NotNull;

import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.form.Form;
import lombok.Data;

@Data
public class RoomForm implements Form {
  private static final long serialVersionUID = 1L;

  @NotNull(message = Msg.NOT_NULL)
  private String roomName;

  @NotNull(message = Msg.NOT_NULL)
  private List<Integer> roomUserIdList;
}
