package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.usecase.gateway.rds.UserRds;
import jp.ddd.server.usecase.gateway.redis.SessionUserRedis;
import jp.ddd.server.domain.entity.user.User;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.UserExt;
import jp.ddd.server.adapter.gateway.redis.entity.SessionUser;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.exception.IllegalDataException;
import jp.ddd.server.domain.repository.UserRepository;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * {@link UserRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserRds userRepository;
    @Autowired
    private SessionUserRedis sessionUserRedis;

    @Override
    public User register(User user) {
        if (isExist(user.getLoginId())) {
            throw new IllegalDataException("登録済みloginIdです。" + user.getLoginId().getId());
        }

        val result = userRepository.save(UserExt.create(user));
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
              return sessionUserRedis.save(sessionUser).getUser();
          }).orElseThrow(() -> new AuthException("invalid loginId and password!" + loginId.getId()));
    }

    @Override
    public void logout(String sid) {
        sessionUserRedis.del(sid);
    }

    @Override
    public boolean isLogin(String sid) {
        return sessionUserRedis.getOpt(sid).isPresent();
    }

    @Override
    public Optional<User> getOptBySid(String sid) {
        return sessionUserRedis.getOpt(sid).map(su -> su.getUser());
    }
}
