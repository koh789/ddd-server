package jp.ddd.server.web.json.message;

import jp.ddd.server.data.message.DelMessageDto;
import jp.ddd.server.utils.Dates;
import jp.ddd.server.web.json.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DelMessageJson implements Json {
  private static final long serialVersionUID = 1L;

  private Long delMessageId;

  private Long lastMessageId;

  private String lastMessageDate;

  private Integer count;

  public static DelMessageJson create(DelMessageDto dto) {
    DelMessageJson json = new DelMessageJson();

    json.setDelMessageId(dto.getDelMessageId());

    json.setLastMessageId(dto.getLastMessageId());
    json.setLastMessageDate(Dates.toString(dto.getLastMessageDate()));
    json.setCount(dto.getCount());

    return json;
  }
}
