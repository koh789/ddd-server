package jp.ddd.server.domain.repository.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.collections.api.list.ImmutableList;

import jp.ddd.server.domain.entity.User;

public interface UserRepositoryCtm {

  Stream<User> findStm(List<Integer> idList);

  ImmutableList<User> find(List<Integer> idList);

  Optional<Map<Integer, User>> findMapOpt(List<Integer> idList);
}
