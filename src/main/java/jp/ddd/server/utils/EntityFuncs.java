package jp.ddd.server.utils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import jp.ddd.server.domain.entity.Administrator;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.data.VisitParam;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.entity.MessageRead;
import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.domain.entity.RoomUser;
import jp.ddd.server.domain.entity.SesAdmin;
import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.domain.entity.User;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.enums.LoginIdType;
import jp.ddd.server.web.form.user.UserForm;

/**
 * entityに変換します。
 *
 * @author noguchi_kohei
 */
public enum EntityFuncs {
  SINGLETON;

  public static BiFunction<UserForm, LoginIdType, User> TO_USER = (form, loginIdType) -> {
    User entity = new User();
    entity.setLoginId(form.getLoginId());
    entity.setLoginIdType(loginIdType.getCode());
    entity.setName(form.getName());
    entity.setPassword(Const.UNDEFINED);
    entity.setDiscription(form.getDescription());

    if (LoginIdType.EMAIL == loginIdType) {

      entity.setEMail(Strings.getEmailWithTrim(form.getLoginId()));
    } else if (LoginIdType.TEL == loginIdType) {

      entity.setTel(Strings.getTelWithTrim(form.getLoginId()));
    }
    entity.setDeleted(Deleted.PUBLIC.getCode());
    entity.setCreateTime(Dates.now());
    entity.setUpdateTime(Dates.now());
    return entity;
  };

  public static Function<User, SesUser> USER_TO_SES_USER = input -> {
    return new SesUser(true, //
      input.getId(),//
      input.getLoginId(),//
      input.getName(),//
      LoginIdType.get(input.getLoginIdType()),//
      input.getDiscription(),//
      input.getEMail(),//
      input.getTel());
  };

  public static Function<Administrator, SesAdmin> ADMIN_TO_SES_ADMIN = input -> {
    return new SesAdmin(input.getId(), //
      input.getAdminId(), //
      input.getUserName(), //
      AdminRole.get(input.getRole()), //
      input.getEMail(), //
      input.getNote());
  };

  /**
   * visitor情報とストア済みsesUser情報を元にsesUser情報を生成します。
   */
  public static BiFunction<VisitParam, SesUser, SesUser> VISITOR_AND_SES_USER_TO_SES_USER = //
    (visitorParam, storedSesUser) -> {
      storedSesUser.setSessionId(visitorParam.getSessionId());
      storedSesUser.setIpAddress(visitorParam.getIpAddress());
      storedSesUser.setReferer(visitorParam.getReferer());
      storedSesUser.setUserAgent(visitorParam.getUserAgent());
      return storedSesUser;
    };

  public static Function<VisitParam, SesUser> VISITOR_TO_SES_USER = input -> {
    SesUser entity = new SesUser();
    entity.setSessionId(input.getSessionId());
    entity.setIpAddress(input.getIpAddress());
    entity.setReferer(input.getReferer());
    entity.setUserAgent(input.getUserAgent());
    return entity;
  };

  public static BiFunction<String, Integer, Room> TO_ROOM = (name, userId) -> {

    Room entity = new Room();

    entity.setName(name);
    entity.setUserId(userId);
    entity.setCount(0);
    entity.setLastMessageDate(Dates.now());
    entity.setDeleted(Deleted.PUBLIC.getCode());
    entity.setCreateTime(Dates.now());
    entity.setUpdateTime(Dates.now());
    return entity;
  };

  public static BiFunction<Room, Message, Room> ROOM_TO_ROOM = (rEnt, mEnt) -> {
    rEnt.setCount(rEnt.getCount() + 1);
    rEnt.setLastMessageDate(Dates.now());
    rEnt.setLastMessageId(mEnt.getId());
    return rEnt;
  };

  public static BiFunction<Integer, List<Integer>, List<RoomUser>> TO_ROOM_USER_LIST = (rid, uidList) -> {
    return uidList.stream().map(uid -> new RoomUser(0, Dates.now(), rid, uid)).collect(Collectors.toList());
  };

  public static BiFunction<Long, Integer, MessageRead> TO_MESSAGE_READ = (messageId, readUserId) -> {

    return new MessageRead(0, Dates.now(), messageId, readUserId);
  };
}
