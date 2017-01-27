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
import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.redis.data.SesUser;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.utils.enums.LoginIdType;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.data.room.RoomDto;
import jp.ddd.server.data.room.RoomWithUsersDto;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.entity.User;

/**
 * dtoへの変換を集約します。
 *
 * @author noguchi_kohei
 */
public enum DtoFuncs {

    SINGLETON;

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
     * {@link RoomWithUsersMsgDto}へメッセージリストなしで変換します。
     */
    public static BiFunction<RoomWithUsersDto, Stream<MessageDto>, RoomWithUsersMsgDto> TO_ROOM_WITH_USERS_MSG = (ruDto, mDtoStm) -> {
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
