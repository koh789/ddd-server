package jp.ddd.server.web.data.form.room;

import jp.ddd.server.web.data.Form;
import lombok.Data;

import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Data
public class RoomUserForm implements Form{

    private List<Integer> userIds;
}
