package jp.ddd.server.adapter.gateway.impl;

import jp.ddd.server.adapter.gateway.external.rdb.ExtUserRepository;
import jp.ddd.server.adapter.gateway.external.redis.SessionUserRepository;
import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.domain.model.user.core.HashPass;
import jp.ddd.server.domain.model.user.core.LoginId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.external.mysql.entity.ExtUser;
import jp.ddd.server.external.redis.entity.SessionUser;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.exception.IllegalDataException;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * {@link UserRepositoryImpl}実装クラスです。
 * 当クラスでドメインモデルと外部プロトコル上のオブジェクトとの
 * インピーダンスミスマッチの解決を集約します。
 * Created by noguchi_kohei
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private ExtUserRepository userRepository;
    @Autowired
    private SessionUserRepository sessionUserRepository;

    @Override
    public User register(User user) {
        if (isExist(user.getLoginId())) {
            throw new IllegalDataException("登録済みloginIdです。" + user.getLoginId().getId());
        }

        val result = userRepository.save(ExtUser.create(user));
        return User.create(result);
    }

    @Override
    public boolean isExist(LoginId loginId) {
        return userRepository.getOpt(loginId.getId()).isPresent();
    }

    @Override
    public Optional<User> getOpt(LoginId loginId, HashPass hashPass) {
        return userRepository.getOpt(loginId.getId(), hashPass.getPass()).map(res -> User.create(res));
    }

    @Override
    public ImmutableList<User> find(ImmutableList<UserId> userIds) {
        return userRepository.find(userIds.collect(uid -> uid.getId())).collect(res -> User.create(res));
    }

    @Override
    public User login(String sid, LoginId loginId, HashPass hashPass) {
        return userRepository.getOpt(loginId.getId(), hashPass.getPass()) //
          .map(u -> {
              val sessionUser = SessionUser.create(sid, User.create(u));
              return sessionUserRepository.save(sessionUser).getUser();
          }).orElseThrow(() -> new AuthException("invalid loginId and password!" + loginId.getId()));
    }

    @Override
    public void logout(String sid) {
        sessionUserRepository.del(sid);
    }

    @Override
    public boolean isLogin(String sid) {
        return sessionUserRepository.getOpt(sid).isPresent();
    }

    @Override
    public Optional<User> getOptBySid(String sid) {
        return sessionUserRepository.getOpt(sid).map(su -> su.getUser());
    }
}
