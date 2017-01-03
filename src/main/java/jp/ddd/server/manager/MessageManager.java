package jp.ddd.server.manager;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import jp.ddd.server.data.message.DelMessageDto;
import jp.ddd.server.data.message.MessageDto;
import jp.ddd.server.data.message.SaveMessageDto;
import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.domain.repository.message.MessageReadRepository;
import jp.ddd.server.domain.repository.message.MessageRepository;
import jp.ddd.server.exception.InternalException;
import jp.ddd.server.exception.NotFoundException;
import jp.ddd.server.utils.Dates;
import jp.ddd.server.utils.EntityFuncs;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.repository.room.RoomRepository;
import jp.ddd.server.utils.DtoFuncs;
import jp.ddd.server.utils.Msg;

/**
 * メッセージ周りのコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Component
public class MessageManager {
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private MessageReadRepository messageReadRepository;

  public Optional<Message> get(Long messageId) {
    return messageRepository.findOpt(messageId);
  }

  public ImmutableList<MessageDto> findMsg(Integer roomId, Map<Integer, UserDto> userMap,
                                           Optional<PageParam> pageOpt) {
    return messageRepository.findByRoomId(roomId, pageOpt) //
      .collect(mEt -> DtoFuncs.USER_DTO_TO_MESSAGE.apply(mEt,
        Optional.ofNullable(userMap.get(mEt.getUserId())))) //
      .collect(mDto -> mDto.createRead(messageReadRepository.find(mDto.getMessageId()), userMap));
  }

  /**
   * roomのメッセージ一覧(ユーザー情報含)を取得しさらに既読情報を更新します。
   */
  public Stream<MessageDto> getMsgStmAndSaveRead(Integer loginUserId, Integer roomId, Map<Integer, UserDto> userMap,
                                                 Optional<PageParam> pageOpt) {
    return messageRepository.findStmByRoomId(roomId, pageOpt)//
      .map(mEnt -> //
        DtoFuncs.USER_DTO_TO_MESSAGE.apply(mEnt, Optional.ofNullable(userMap.get(mEnt.getUserId()))))
      .map(mDto -> {//
        if (!loginUserId.equals(mDto.getMessageUserId())) {
          messageReadRepository.saveByUnique(mDto.getMessageId(), loginUserId, true);
        }
        return mDto;
      }) //
      .map(mDto -> mDto.createRead(messageReadRepository.findStm(mDto.getMessageId()), userMap));
  }

  @Transactional(rollbackFor = Exception.class)
  public Optional<SaveMessageDto> register(Integer roomId, Integer userId, String content) {

    roomRepository.findOpt(roomId).orElseThrow(() -> {
      return new NotFoundException(Msg.NOT_FOUND_ROOM + " roomId: " + roomId, Msg.NOT_FOUND_ROOM);
    });
    Message mEnt = messageRepository.save(new Message(0, content, Dates.now(), roomId, userId));

    return roomRepository.findOpt(roomId).map(rEnt -> {

      Room updateRoomEntity = roomRepository.save(EntityFuncs.ROOM_TO_ROOM.apply(rEnt, mEnt));
      return new SaveMessageDto(mEnt.getId(), Dates.now(), ((Integer) updateRoomEntity.getCount()).longValue());
    });
  }

  /**
   * 指定messageを削除します。
   * 削除の際に既読情報も論理削除します。
   */
  @Transactional(rollbackFor = Exception.class)
  public DelMessageDto delete(Integer roomId, Integer userId, Long messageId) {
    messageRepository.logicalDelete(messageId);
    messageReadRepository.logicalDelete(messageId);

    Room roomEntity = roomRepository.findOpt(roomId)
      .orElseThrow(() -> new InternalException("対象メッセージのroomが存在しませんでした。roomId: " + roomId));

    //削除対象が最新メッセージの場合次のメッセージに更新する。
    if (roomEntity.getLastMessageId().equals(messageId)) {
      Optional<Message> msgEntityOpt = messageRepository.findStmByRoomId(roomId).findFirst();

      if (msgEntityOpt.isPresent()) {
        Message msgEntity = msgEntityOpt.get();
        Room rEntity = roomRepository.countDown(roomId, msgEntity.getId(), msgEntity.getMessageDate())
          .orElseThrow(() -> new InternalException("roomカウントダウン処理中に異常が発生しました。roomId" + roomId));
        return new DelMessageDto(messageId, rEntity.getLastMessageId(), rEntity.getLastMessageDate(),
          rEntity.getCount());
      } else {
        Room rEntity = roomRepository.countDown(roomId, null, roomEntity.getCreateTime())
          .orElseThrow(() -> new InternalException("roomカウントダウン処理中に異常が発生しました。roomId" + roomId));
        return new DelMessageDto(messageId, rEntity.getLastMessageId(), rEntity.getLastMessageDate(),
          rEntity.getCount());
      }
    } else {
      Room rEntity = roomRepository.countDown(roomId)
        .orElseThrow(() -> new InternalException("roomカウントダウン処理中に異常が発生しました。roomId" + roomId));
      return new DelMessageDto(messageId, rEntity.getLastMessageId(), rEntity.getLastMessageDate(),
        rEntity.getCount());
    }
  }
}
