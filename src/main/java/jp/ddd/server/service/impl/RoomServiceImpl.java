package jp.ddd.server.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.data.message.MessageDto;
import jp.ddd.server.data.room.RoomWithUsersMsgDto;
import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.manager.MessageManager;
import jp.ddd.server.service.RoomService;
import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.manager.RoomManager;
import jp.ddd.server.utils.DtoFuncs;

/**
 * メッセージルーム関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Service
public class RoomServiceImpl implements RoomService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private RoomManager roomManager;
  @Autowired
  private MessageManager messageManager;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Integer register(Integer userId, String roomName, List<Integer> roomUserIdList) {
    return roomManager.register(userId, roomName, roomUserIdList);
  }

  /**
   * ユーザーが持つルームのリストを取得します。
   */
  @Override
  public ImmutableList<RoomWithUsersDto> findRoom(Integer userId) {
    return roomManager.findRoomWithUser(userId);
  }

  /**
   * roomのユーザ一覧情報を取得します。
   * 同時にルームメッセージの既読情報も更新します。
   **/
  @Override
  public Optional<RoomWithUsersMsgDto> getRoomWithUsersMsgOpt(Integer loginUserId, Integer roomId,
                                                              Optional<PageParam> pageOpt) {
    return roomManager.getRoomWithUsersOpt(roomId).map(ru -> {
      Map<Integer, UserDto> userMap = ru.getUserStm().collect(Collectors.toMap(u -> u.getUserId(), u -> u));

      Stream<MessageDto> mDtoStm = messageManager.getMsgStmAndSaveRead(loginUserId, roomId, userMap, pageOpt);

      return DtoFuncs.TO_ROOM_WITH_USERS_MSG.apply(ru, mDtoStm);
    });
  }

  /**
   * ユーザーがルームユーザーか判定します。
   */
  @Override
  public boolean isRoomUser(Integer loginUserId, Integer roomId) {
    return roomManager.findRoomUserId(roomId).anySatisfy(rUserId -> rUserId.equals(loginUserId));
  }
}
