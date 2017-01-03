package jp.ddd.server.data.room;

import java.util.List;

import jp.ddd.server.data.BaseDto;
import jp.ddd.server.data.message.MessageDto;
import jp.ddd.server.data.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomWithUsersMsgDto extends BaseDto {
  private static final long serialVersionUID = 1L;
  private Integer roomId;

  private String name;
  /**
   * room作成者
   */
  private Integer creaeteUserId;

  private String createUserName;

  private String createLoginId;

  /**
   * ルームメンバー
   */
  private List<UserDto> userList;

  private List<MessageDto> messageList;

  private int count;

  private String lastMessageDate;

  private Long lastMessageId;
}
