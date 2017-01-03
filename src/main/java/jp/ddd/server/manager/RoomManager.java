package jp.ddd.server.manager;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.domain.entity.User;
import jp.ddd.server.domain.repository.message.MessageRepository;
import jp.ddd.server.domain.repository.room.RoomRepository;
import jp.ddd.server.domain.repository.room.RoomUserRepository;
import jp.ddd.server.domain.repository.user.UserRepository;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.DtoFuncs;
import jp.ddd.server.utils.EntityFuncs;
import jp.ddd.server.utils.PsLists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.data.room.RoomDto;

/**
 * メッセージルーム周りのコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Component
public class RoomManager {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomUserRepository roomUserRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 新しいメッセージルーム・ルームユーザー情報を作成します。
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer register(Integer userId, String roomName, List<Integer> roomUserIdList) {
        Integer roomId = roomRepository.save(EntityFuncs.TO_ROOM.apply(roomName, userId)).getId();

        if (!roomUserIdList.contains(userId)) {
            roomUserIdList.add(userId);
        }
        roomUserRepository.save(EntityFuncs.TO_ROOM_USER_LIST.apply(roomId, roomUserIdList));

        return roomId;
    }

    /**
     * ユーザーが持つルームの一覧を取得します。
     */
    public ImmutableList<RoomWithUsersDto> findRoomWithUser(Integer userId) {

        ImmutableList<Optional<RoomWithUsersDto>> roomWithUserOpts = roomRepository.find(userId)
          .collect(rEnt -> getRoomOpt(rEnt.getId()).map(rDto -> {
              ImmutableList<Integer> uIdList = roomUserRepository.findUserId(rDto.getRoomId());

              if (uIdList.isEmpty()) {
                  return DtoFuncs.TO_ROOM_WITH_USERS.apply(rDto, null);
              }
              Optional<Map<Integer, User>> uEntityMapOpt = userRepository.findMapOpt(uIdList.toList());

              ImmutableList<User> uList = uEntityMapOpt.map(uMap -> uIdList.collect(uid -> uMap.get(uid)))
                .orElse(Lists.immutable.empty());

              return DtoFuncs.TO_ROOM_WITH_USERS.apply(rDto, uList.toList());
          }));
        return PsLists.toImt(roomWithUserOpts);
    }

    /**
     * roomIdからroomDto(roomUser,roomLastMessage情報付き)を取得します。
     */
    public Optional<RoomDto> getRoomOpt(Integer roomId) {
        Optional<RoomDto> roomDtoOpt = roomRepository.findOpt(roomId)
          .flatMap(rEntity -> userRepository.findByIdAndDeleted(rEntity.getUserId(), Deleted.PUBLIC.getCode())
            .map(uEntity -> DtoFuncs.TO_ROOM.apply(rEntity, uEntity)));

        return roomDtoOpt //
          .flatMap(rDto -> Optional.ofNullable(rDto.getLastMessageId()) //
            .flatMap(lmid -> messageRepository.findOpt(lmid) //
              .map(mEnt -> {
                  rDto.setLastMessage(mEnt.getContent());
                  return rDto;
              })) //

          );
    }

    /**
     * ルーム情報を参加ユーザー一覧付きで取得します。
     */
    public Optional<RoomWithUsersDto> getRoomWithUsersOpt(Integer roomId) {
        Optional<Room> roomEntityOpt = roomRepository.findOpt(roomId);

        return roomEntityOpt //
          .flatMap(rEnt -> getRoomOpt(rEnt.getId()) //
            .map(rDto -> {
                List<Integer> uIdList = roomUserRepository.findUserIdStm(rEnt.getId())
                  .collect(Collectors.toList());

                if (CollectionUtils.isEmpty(uIdList)) {
                    return DtoFuncs.TO_ROOM_WITH_USERS.apply(rDto, null);
                }

                List<User> uList = userRepository.findMapOpt(uIdList)
                  .map(uEntityMap -> uIdList.stream().map(uid -> uEntityMap.get(uid)))
                  .orElse(Stream.empty()).collect(Collectors.toList());

                return DtoFuncs.TO_ROOM_WITH_USERS.apply(rDto, uList);
            }));
    }

    /**
     * ルームに存在するユーザIDのlistを取得します。
     */
    public ImmutableList<Integer> findRoomUserId(Integer roomId) {
        return roomUserRepository.findUserId(roomId);
    }
}
