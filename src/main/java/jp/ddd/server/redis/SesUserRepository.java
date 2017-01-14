package jp.ddd.server.redis;

import jp.ddd.server.redis.data.SesUser;

import java.util.Optional;

/**
 * セッション情報として扱うユーザ情報の
 * キャッシュ処理を管理します。
 *
 * @author noguchi_kohei
 */
public interface SesUserRepository {

    SesUser save(String sid, SesUser sesUser);

    Optional<SesUser> getOpt(String sid);

    void del(String sid);
}
