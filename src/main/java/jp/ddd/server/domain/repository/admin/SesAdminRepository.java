package jp.ddd.server.domain.repository.admin;

import java.util.Optional;

import jp.ddd.server.domain.entity.SesAdmin;

public interface SesAdminRepository {

  void save(String sid, SesAdmin sesAdmin);

  SesAdmin get(String sid);

  Optional<SesAdmin> getOpt(String sid);

  void del(String sid);
}
