package jp.ddd.server.web.json.room;

import jp.ddd.server.web.json.BaseJson;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterRoomJson extends BaseJson {
  private static final long serialVersionUID = 1L;

  Integer roomId;

  public static RegisterRoomJson create(Integer roomId) {
    return new RegisterRoomJson(roomId);
  }
}
