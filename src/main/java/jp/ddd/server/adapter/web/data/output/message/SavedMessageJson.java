package jp.ddd.server.adapter.web.data.output.message;

import jp.ddd.server.adapter.repository.mysql.entity.Message;
import jp.ddd.server.other.utils.Dates;
import jp.ddd.server.adapter.web.data.Json;
import lombok.Builder;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedMessageJson implements Json {

    private Long messageId;
    /** yyyy/MM/dd hh:mm:ss */
    private String messageDt;
    /** yyyy/MM/dd hh:mm:ss */
    private String lastEditDt;

    public static SavedMessageJson create(Message message) {
        return SavedMessageJson.builder().messageId(message.getId()).messageDt(Dates.toString(message.getMessageDt()))
          .lastEditDt(Dates.toString(message.getLastEditDt())).build();
    }
}
