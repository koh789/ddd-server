package jp.ddd.server.domain.repository.user;

import java.util.Optional;

import jp.ddd.server.domain.entity.SesUser;

/**
 * セッション情報として扱うユーザ情報の
 * キャッシュ処理を管理します。
 *
 * @author noguchi_kohei
 */
public interface SesUserRepository {

  void save(String sid, SesUser sesUser);

  SesUser get(String sid);

  Optional<SesUser> getOpt(String sid);

  void del(String sid);
}
