package jp.ddd.server.data.message;

import java.util.Date;

import jp.ddd.server.data.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveMessageDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long messageId;

  private Date lastMessageDate;

  private Long count;
}
