package jp.ddd.server.adapter.web.presenter.output.message;

import jp.ddd.server.adapter.web.presenter.output.Json;
import jp.ddd.server.external.mysql.entity.ExtMessage;
import jp.ddd.server.other.utils.Dates;
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

    public static SavedMessageJson create(ExtMessage message) {
        return SavedMessageJson.builder().messageId(message.getId()).messageDt(Dates.toString(message.getMessageDt()))
          .lastEditDt(Dates.toString(message.getLastEditDt())).build();
    }
}
