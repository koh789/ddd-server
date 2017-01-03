package jp.ddd.server.data.message;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.data.BaseDto;
import jp.ddd.server.utils.Dates;
import org.eclipse.collections.api.list.ImmutableList;

import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.domain.entity.MessageRead;
import jp.ddd.server.utils.PsLists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メッセージを表現するdtoです。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Integer roomId;

  private Long messageId;

  private Integer messageUserId;

  private String messageUserName;

  private String messageLoginId;

  private String content;

  private Date messageDate;

  private List<ReadDto> readList;

  /**
   * messageDtoの既読情報のみ作成します。
   */
  public MessageDto createRead(Stream<MessageRead> messageReadStm, Map<Integer, UserDto> userMap) {

    List<ReadDto> readList = messageReadStm.map(mrEnt -> {

      ReadDto readDto = new ReadDto();
      readDto.setMesseageId(mrEnt.getMessageId());
      readDto.setReadUserId(mrEnt.getUserId());
      readDto.setReadDate(Dates.toString(mrEnt.getDate()));

      UserDto userDto = userMap.get(mrEnt.getUserId());
      if (userDto != null) {
        readDto.setReadUserName(userDto.getName());
        readDto.setReadLoginId(userDto.getLoginId());
      }

      return readDto;
    }).collect(Collectors.toList());
    this.setReadList(readList);
    return this;
  }

  /**
   * messageDtoの既読情報のみ作成します。
   */
  public MessageDto createRead(ImmutableList<MessageRead> messageReadList, Map<Integer, UserDto> userMap) {

    ImmutableList<ReadDto> readList = messageReadList.collect(mrEt -> {
      ReadDto readDto = new ReadDto();
      readDto.setMesseageId(mrEt.getMessageId());
      readDto.setReadUserId(mrEt.getUserId());
      readDto.setReadDate(Dates.toString(mrEt.getDate()));

      UserDto userDto = userMap.get(mrEt.getUserId());
      if (userDto != null) {
        readDto.setReadUserName(userDto.getName());
        readDto.setReadLoginId(userDto.getLoginId());
      }

      return readDto;
    });
    this.setReadList(PsLists.toList(readList));
    return this;
  }
}
