package jp.ddd.server.web.json.message;

import java.util.List;

import jp.ddd.server.data.message.MessageDto;
import jp.ddd.server.data.room.RoomWithUsersMsgDto;
import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.web.json.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomMessageJson implements Json {
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

    public static RoomMessageJson create(RoomWithUsersMsgDto dto) {
        return new RoomMessageJson(//
          dto.getRoomId(), //
          dto.getName(),//
          dto.getCreaeteUserId(), //
          dto.getCreateUserName(), //
          dto.getCreateLoginId(), //
          dto.getUserList(), //
          dto.getMessageList(), //
          dto.getCount(), //
          dto.getLastMessageDate(), //
          dto.getLastMessageId());
    }
}
