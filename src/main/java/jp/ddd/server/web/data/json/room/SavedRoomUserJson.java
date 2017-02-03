package jp.ddd.server.web.data.json.room;

import jp.ddd.server.web.data.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedRoomUserJson implements Json {
    private static final long serialVersionUID = -6715594480633791675L;

    private Integer userId;

    private String joinDt;

    public static SavedRoomUserJson create(Integer userId, String joinDt) {
        return SavedRoomUserJson.builder().userId(userId).joinDt(joinDt).build();
    }
}
