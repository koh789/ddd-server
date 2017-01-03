package jp.ddd.server.domain.repository.room.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.domain.repository.room.RoomRepositoryCtm;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.PsLists;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class RoomRepositoryImpl implements RoomRepositoryCtm {

  @Autowired
  private EntityManager em;

  @Override
  public Optional<Room> findOpt(Integer id) {
    List<Room> entityList = em.createNamedQuery("Room.findById", Room.class).setParameter("id", id)
      .setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return CollectionUtils.isEmpty(entityList) ? Optional.empty() : Optional.of(entityList.get(0));
  }

  @Override
  public Stream<Room> findStm(Integer userId) {

    return find(userId).toList().stream();
  }

  @Override
  public ImmutableList<Room> find(Integer userId) {
    List<Room> entityList = em.createNamedQuery("Room.findByUserIdOrderByMsgDateDesc", Room.class)
      .setParameter("userId", userId).setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return PsLists.toImt(entityList);
  }

  @Override
  public Optional<Room> countDown(Integer id, Long lastMessageId, Date lastMessageDate) {
    Optional<Room> entityOpt = findOpt(id);
    if (entityOpt.isPresent()) {
      Room entity = entityOpt.get();
      entity.setCount(entity.getCount() - 1);
      entity.setLastMessageId(lastMessageId);
      entity.setLastMessageDate(lastMessageDate);
      em.merge(entity);
    }
    return entityOpt;
  }

  @Override
  public Optional<Room> countDown(Integer id) {
    Optional<Room> entityOpt = findOpt(id);
    if (entityOpt.isPresent()) {
      Room entity = entityOpt.get();
      entity.setCount(entity.getCount() - 1);
      em.merge(entity);
    }
    return entityOpt;
  }

}
