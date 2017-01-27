package jp.ddd.server.redis.impl;

import jp.ddd.server.redis.base.Key;
import jp.ddd.server.redis.base.RedisMapper;
import jp.ddd.server.redis.data.SesUser;
import jp.ddd.server.redis.SesUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * セッション情報として扱うユーザ情報の
 * キャッシュ処理を管理します。
 *
 * @author noguchi_kohei
 */
@Repository
public class SesUserRepositorImpl extends RedisMapper<SesUser> implements SesUserRepository {

    private String nameSpace = "SesUserRepositoryRedis";

    @Override
    public SesUser save(String sid, SesUser sesUser) {
        return getOpt(sid) //
          .orElseGet(() -> {
              saveValue(generateStrKey(SesUser.class, nameSpace, sid), sesUser);
              return sesUser;
          });
    }

    @Override
    public Optional<SesUser> getOpt(String sid) {
        Key key = generateStrKey(SesUser.class, nameSpace, sid);
        return Optional.ofNullable(getValue(key));
    }

    @Override
    public void del(String sid) {
        template.delete(generateStrKey(SesUser.class, nameSpace, sid));
    }
}
