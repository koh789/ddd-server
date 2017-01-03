package jp.ddd.server.web.json.message;

import jp.ddd.server.data.message.SaveMessageDto;
import jp.ddd.server.utils.Dates;
import jp.ddd.server.web.json.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostMessageJson implements Json {
  private static final long serialVersionUID = 1L;

  private Long messageId;

  private String lastMessageDate;

  private Long count;

  public static PostMessageJson create(SaveMessageDto dto) {
    return new PostMessageJson(//
      dto.getMessageId(), //
      Dates.toString(dto.getLastMessageDate()),//
      dto.getCount());
  }

}
