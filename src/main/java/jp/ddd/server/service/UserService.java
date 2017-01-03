package jp.ddd.server.service;

import jp.ddd.server.web.form.user.UserForm;
import jp.ddd.server.data.user.UserDto;

/**
 * ユーザー関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
public interface UserService {

  Integer register(UserForm userForm);

  void login(String sid, String loginId, String password);

  UserDto get(Integer userId);
}
