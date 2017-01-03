package jp.ddd.server.domain.repository.room.impl;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import jp.ddd.server.domain.entity.RoomUser;
import jp.ddd.server.domain.repository.room.RoomUserRepositoryCtm;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.PsLists;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class RoomUserRepositoryImpl implements RoomUserRepositoryCtm {

  @Autowired
  private EntityManager em;

  @Override
  public Stream<RoomUser> findStm(Integer roomId) {
    List<RoomUser> entityList = em.createNamedQuery("RoomUser.findByRoomId", RoomUser.class) //
      .setParameter("roomId", roomId) //
      .setParameter("deleted", Deleted.PUBLIC.getCode()) //
      .getResultList();

    return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
  }

  @Override
  public ImmutableList<RoomUser> find(Integer roomId) {
    List<RoomUser> entityList = em.createNamedQuery("RoomUser.findByRoomId", RoomUser.class) //
      .setParameter("roomId", roomId) //
      .setParameter("deleted", Deleted.PUBLIC.getCode()) //
      .getResultList();

    return PsLists.toImt(entityList);
  }

  @Override
  public Stream<Integer> findUserIdStm(Integer roomId) {
    return findStm(roomId).map(ruEnt -> ruEnt.getUserId());
  }

  @Override
  public ImmutableList<Integer> findUserId(Integer roomId) {
    return find(roomId).collect(rEnt -> rEnt.getUserId());
  }

}
