package jp.ddd.server.service.impl;

import jp.ddd.server.exception.IllegalArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.domain.entity.User;
import jp.ddd.server.domain.repository.user.UserRepository;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.enums.LoginIdType;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.exception.NotFoundException;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.service.UserService;
import jp.ddd.server.utils.DtoFuncs;
import jp.ddd.server.utils.EntityFuncs;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.utils.Strings;
import jp.ddd.server.web.form.user.UserForm;

/**
 * ユーザー関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Service
public class UserServiceImpl implements UserService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private VisitorManager visitorManager;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer register(UserForm userForm) {
    if (userRepository.findByLoginIdAndDeleted(userForm.getLoginId(), Deleted.PUBLIC.getCode()).isPresent()) {
      throw new IllegalArgumentException(userForm.getLoginId() + "は登録済みです。", true);
    }

    User entity = userRepository
      .save(EntityFuncs.TO_USER.apply(userForm, LoginIdType.getBy(userForm.getLoginId())));

    String hashedPass = Strings.hashPass(userForm.getPassword(), String.valueOf(entity.getId()));

    entity.setPassword(hashedPass);
    userRepository.save(entity);
    return entity.getId();
  }

  @Override
  public void login(String sid, String loginId, String password) {
    User entity = userRepository.findByLoginIdAndDeleted(loginId, Deleted.PUBLIC.getCode())
      .orElseThrow(() -> new NotFoundException(Msg.INVALID_LOGIN_ID, true));
    String hashedPass = Strings.hashPass(password, String.valueOf(entity.getId()));

    if (!hashedPass.equals(entity.getPassword())) {
      throw new AuthException(Msg.INVALID_PASS, true);
    }

    visitorManager.loginUser(sid, EntityFuncs.USER_TO_SES_USER.apply(entity));
  }

  @Override
  public UserDto get(Integer userId) {
    User user = userRepository.findByIdAndDeleted(userId, Deleted.PUBLIC.getCode())
      .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_USER, true));

    return DtoFuncs.TO_USER.apply(user);
  }
}
