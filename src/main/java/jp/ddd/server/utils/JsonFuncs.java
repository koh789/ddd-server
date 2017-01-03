package jp.ddd.server.utils;

import java.util.function.Function;

import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.web.json.user.UserJson;

/**
 * jsonに変換します。
 *
 * @author noguchi_kohei
 */
public enum JsonFuncs {
  SINGLETON;

  public static Function<UserDto, UserJson> TO_USER_JSON = input -> {
    return new UserJson( //
      input.getUserId(), //
      input.getLoginId(), //
      input.getName(), //
      input.getLoginIdType().getValue(), //
      input.getDiscription(), //
      input.getEMail(), //
      input.getTel());
  };
}
