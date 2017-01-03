package jp.ddd.server.domain.repository.admin.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.ddd.server.domain.entity.SesAdmin;
import jp.ddd.server.domain.repository.admin.SesAdminRepository;
import jp.ddd.server.domain.repository.base.Key;
import jp.ddd.server.domain.repository.base.RedisMapper;

@Repository
public class SesAdminRepositoryRedis extends RedisMapper<SesAdmin> implements SesAdminRepository {

  private String nameSpace = "SesAdminRepositoryRedis";

  @Override
  public void save(String sid, SesAdmin sesAdmin) {
    Key key = generateStrKey(SesAdmin.class, nameSpace, sid);
    saveValue(key, sesAdmin);
  }

  @Override
  public SesAdmin get(String sid) {
    return getValue(generateStrKey(SesAdmin.class, nameSpace, sid));
  }

  @Override
  public Optional<SesAdmin> getOpt(String sid) {
    Key key = generateStrKey(SesAdmin.class, nameSpace, sid);
    return Optional.ofNullable(getValue(key));
  }

  @Override
  public void del(String sid) {
    template.delete(generateStrKey(SesAdmin.class, nameSpace, sid));
  }

}
