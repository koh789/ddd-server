package jp.ddd.server.service;

import java.util.List;
import java.util.Optional;

import jp.ddd.server.data.room.RoomWithUsersMsgDto;
import org.eclipse.collections.api.list.ImmutableList;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.data.room.RoomWithUsersDto;

/**
 * メッセージルーム関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
public interface RoomService {

  Integer register(Integer userId, String roomName, List<Integer> roomUserIdList);

  /**
   * ユーザーが持つルームのstmを取得します。
   */
  ImmutableList<RoomWithUsersDto> findRoom(Integer userId);

  /**
   * roomのユーザ一覧情報を取得します。
   */
  Optional<RoomWithUsersMsgDto> getRoomWithUsersMsgOpt(Integer loginUserId, Integer roomId,
                                                       Optional<PageParam> pageOpt);

  /**
   * ルームに存在するユーザIDのstmを取得します。
   */
  boolean isRoomUser(Integer userId, Integer roomId);
}
