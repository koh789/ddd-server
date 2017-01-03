package jp.ddd.server.domain.repository.test.impl;

import jp.ddd.server.domain.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jp.ddd.server.domain.repository.test.TestRepository;

@Repository
public class TestRepositoryRedis implements TestRepository {
  @Autowired
  private RedisTemplate<String, Test> template;

  @Override
  public void save(Long keyId, String text, String info) {
    template.opsForValue().set(String.valueOf(keyId), new Test(keyId, text, info));
  }
}
