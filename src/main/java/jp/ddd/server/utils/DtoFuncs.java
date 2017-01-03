package jp.ddd.server.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.data.admin.AdminDto;
import jp.ddd.server.data.message.MessageDto;
import jp.ddd.server.data.room.RoomWithUsersMsgDto;
import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.domain.entity.Administrator;
import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.utils.enums.LoginIdType;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.data.room.RoomDto;
import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.domain.entity.User;

/**
 * dtoへの変換を集約します。
 *
 * @author noguchi_kohei
 */
public enum DtoFuncs {

  SINGLETON;

  public static Function<Administrator, AdminDto> TO_ADMIN = admin -> {
    AdminDto dto = new AdminDto();

    dto.setId(admin.getId());
    dto.setAdminId(admin.getAdminId());
    dto.setAdminRole(AdminRole.get(admin.getRole()));
    dto.setName(admin.getUserName());
    dto.seteMail(dto.geteMail());
    dto.setNote(admin.getNote());
    return dto;
  };

  public static Function<User, UserDto> TO_USER = user -> {
    UserDto dto = new UserDto();
    dto.setUserId(user.getId());
    dto.setLoginId(user.getLoginId());
    dto.setName(user.getName());
    dto.setLoginIdType(LoginIdType.get(user.getLoginIdType()));
    dto.setDiscription(user.getDiscription());
    dto.setEMail(Strings.isEmpty(user.getEMail()) ? null : user.getEMail());
    dto.setTel(Strings.isEmpty(user.getTel()) ? null : user.getTel());
    return dto;
  };

  public static Function<List<User>, List<UserDto>> TO_USER_LIST = userList -> {
    if (CollectionUtils.isEmpty(userList)) {
      return null;
    }
    return userList.stream() //
      .map(input -> TO_USER.apply(input)) //
      .collect(Collectors.toList());
  };

  public static Function<SesUser, UserDto> SES_TO_USER = input -> {

    UserDto dto = new UserDto();
    dto.setUserId(input.getUserId());
    dto.setLoginId(input.getLoginId());
    dto.setName(input.getName());
    dto.setLoginIdType(input.getLoginIdType());
    dto.setDiscription(input.getDiscription());
    dto.setEMail(Strings.isEmpty(input.getEMail()) ? null : input.getEMail());
    dto.setTel(Strings.isEmpty(input.getTel()) ? null : input.getTel());
    dto.setLoginUser(true);
    return dto;
  };

  /**
   * {@link RoomDto}のlastMessage以外のデータを作成します。
   */
  public static BiFunction<Room, User, RoomDto> TO_ROOM = (room, user) -> {

    return new RoomDto(room.getId(),//
      room.getName(),//
      user.getId(), //
      user.getLoginId(),//
      user.getName(),//
      room.getCount(), //
      Dates.toString(room.getLastMessageDate(), Dates.VIEW_DATE_FORMAT),//
      room.getLastMessageId(), //
      null);
  };

  /**
   * {@link RoomWithUsersMsgDto}へメッセージリストなしで変換します。
   */
  public static BiFunction<RoomDto, List<User>, RoomWithUsersDto> TO_ROOM_WITH_USERS = (roomDto, userList) -> {
    return new RoomWithUsersDto(roomDto.getRoomId(), //
      roomDto.getName(), //
      roomDto.getCreaeteUserId(), //
      roomDto.getCreateUserName(), //
      roomDto.getCreateLoginId(), //
      TO_USER_LIST.apply(userList), //
      roomDto.getCount(),//
      roomDto.getLastMessageDate(), //
      roomDto.getLastMessageId(), //
      roomDto.getLastMessage());
  };

  /**
   * {@link RoomWithUsersMsgDto}へメッセージリストなしで変換します。
   */
  public static BiFunction<RoomWithUsersDto, Stream<MessageDto>, RoomWithUsersMsgDto> TO_ROOM_WITH_USERS_MSG = (ruDto,
                                                                                                                mDtoStm) -> {
    return new RoomWithUsersMsgDto(//
      ruDto.getRoomId(), //
      ruDto.getName(), //
      ruDto.getCreaeteUserId(), //
      ruDto.getCreateUserName(), //
      ruDto.getCreateLoginId(),  //
      ruDto.getUserList(),  //
      mDtoStm.collect(Collectors.toList()),  //
      ruDto.getCount(), //
      ruDto.getLastMessageDate(), //
      ruDto.getLastMessageId());
  };

  public static BiFunction<Message, Optional<UserDto>, MessageDto> USER_DTO_TO_MESSAGE = (message, uDtoOpt) -> {
    MessageDto dto = new MessageDto();
    dto.setRoomId(message.getRoomId());
    dto.setMessageId(message.getId());
    dto.setMessageUserId(message.getUserId());
    if (uDtoOpt.isPresent()) {//ユーザーが退会済みの場合セットしない。
      dto.setMessageUserName(uDtoOpt.get().getName());
      dto.setMessageLoginId(uDtoOpt.get().getLoginId());
    }
    dto.setContent(message.getContent());
    return dto;
  };
}
