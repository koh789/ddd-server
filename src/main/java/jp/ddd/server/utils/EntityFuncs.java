package jp.ddd.server.utils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.data.VisitParam;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.entity.MessageRead;
import jp.ddd.server.domain.entity.Room;
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

}
