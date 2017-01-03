package jp.ddd.server.data.room;

import jp.ddd.server.data.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto extends BaseDto {
  private static final long serialVersionUID = 1L;
  private Integer roomId;

  private String name;
  /**
   * room作成者
   */
  private Integer creaeteUserId;

  private String createLoginId;

  private String createUserName;

  private int count;

  private String lastMessageDate;

  private Long lastMessageId;

  private String lastMessage;
}
