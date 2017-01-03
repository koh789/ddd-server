package jp.ddd.server.domain.repository.user.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import jp.ddd.server.domain.repository.user.UserRepositoryCtm;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.PsLists;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.domain.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepositoryCtm {
  @Autowired
  private EntityManager em;

  @Override
  public Stream<User> findStm(List<Integer> idList) {
    List<User> eList = em.createNamedQuery("User.findByIdList", User.class)//
      .setParameter("idList", idList)//
      .setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();
    return CollectionUtils.isEmpty(eList) ? Stream.empty() : eList.stream();
  }

  @Override
  public ImmutableList<User> find(List<Integer> idList) {
    List<User> entityList = em.createNamedQuery("User.findByIdList", User.class)//
      .setParameter("idList", idList)//
      .setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();
    return PsLists.toImt(entityList);
  }

  @Override
  public Optional<Map<Integer, User>> findMapOpt(List<Integer> idList) {
    return Optional.ofNullable(findStm(idList).collect(//
      Collectors.toMap(u -> u.getId(), u -> u)));
  }

}
