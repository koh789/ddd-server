package jp.ddd.server.adapter.gateway.external.redis;

import jp.ddd.server.external.redis.entity.SessionUser;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface SessionUserRepository {

    SessionUser save(SessionUser sessionUser);

    Optional<SessionUser> getOpt(String sid);

    void del(String sid);
}
