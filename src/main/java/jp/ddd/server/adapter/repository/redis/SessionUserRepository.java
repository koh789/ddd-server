package jp.ddd.server.adapter.repository.redis;

import jp.ddd.server.adapter.repository.redis.entity.SessionUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface SessionUserRepository {

    SessionUser save(SessionUser sessionUser);

    Optional<SessionUser> getOpt(String sid);

    void del(String sid);
}
