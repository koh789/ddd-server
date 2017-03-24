package jp.ddd.server.adapter.gateway.dynamodb.table;

import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.other.utils.Dates;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by noguchi_kohei 
 */
@NoArgsConstructor
@Data
public class MessageReadDyn implements BaseDyn {
    private static final long serialVersionUID = 8630209538476323532L;
    private Long messageId;
    private Integer userId;
    private String readAt;

    public Date getReadAdWithConvert() {
        return Dates.toDate(this.readAt, Dates.DEFAULT_DATE_FORMAT);
    }
}
