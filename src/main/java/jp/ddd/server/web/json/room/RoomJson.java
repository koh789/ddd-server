package jp.ddd.server.web.json.room;

import java.util.List;

import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.web.json.Json;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RoomJson implements Json {
    private static final long serialVersionUID = 1L;

    private List<RoomWithUsersDto> list;

    public static RoomJson create(List<RoomWithUsersDto> list) {
        return new RoomJson(list);
    }
}
