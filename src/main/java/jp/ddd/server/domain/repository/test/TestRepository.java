package jp.ddd.server.domain.repository.test;

public interface TestRepository {

  void save(Long keyId, String text, String info);
}
