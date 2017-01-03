package jp.ddd.server.data.message;

import java.util.Date;

import jp.ddd.server.data.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DelMessageDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long delMessageId;

  private Long lastMessageId;
  /**
   * roomの最新メッセージ
   */
  private Date lastMessageDate;
  /**
   * roomのメッセージ数
   */
  private Integer count;
}
