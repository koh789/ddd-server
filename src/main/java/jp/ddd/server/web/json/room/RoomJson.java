package jp.ddd.server.web.json.room;

import java.util.List;

import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.web.json.BaseJson;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RoomJson extends BaseJson {
  private static final long serialVersionUID = 1L;

  private List<RoomWithUsersDto> list;

  public static RoomJson create(List<RoomWithUsersDto> list) {
    return new RoomJson(list);
  }
}
