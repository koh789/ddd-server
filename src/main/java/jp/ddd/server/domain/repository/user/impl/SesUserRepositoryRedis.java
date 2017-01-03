package jp.ddd.server.domain.repository.user.impl;

import java.util.Optional;

import jp.ddd.server.domain.repository.base.Key;
import jp.ddd.server.domain.repository.base.RedisMapper;
import jp.ddd.server.domain.repository.user.SesUserRepository;
import org.springframework.stereotype.Repository;

import jp.ddd.server.domain.entity.SesUser;

/**
 * セッション情報として扱うユーザ情報の
 * キャッシュ処理を管理します。
 *
 * @author noguchi_kohei
 */
@Repository
public class SesUserRepositoryRedis extends RedisMapper<SesUser> implements SesUserRepository {

    private String nameSpace = "SesUserRepositoryRedis";

    @Override
    public void save(String sid, SesUser sesUser) {
        Key key = generateStrKey(SesUser.class, nameSpace, sid);
        saveValue(key, sesUser);
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
