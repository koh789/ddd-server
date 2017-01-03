package jp.ddd.server.data.room;

import java.util.List;
import java.util.stream.Stream;

import jp.ddd.server.data.BaseDto;
import jp.ddd.server.data.user.UserDto;
import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomWithUsersDto extends BaseDto {
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

  private int count;

  private String lastMessageDate;

  private Long lastMessageId;

  private String lastMessage;

  public Stream<UserDto> getUserStm() {
    return CollectionUtils.isEmpty(userList) ? Stream.empty() : userList.stream();
  }
}
