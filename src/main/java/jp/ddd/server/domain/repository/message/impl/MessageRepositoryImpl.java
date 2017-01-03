package jp.ddd.server.domain.repository.message.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.PsLists;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.domain.repository.message.MessageRepositoryCtm;

@Repository
public class MessageRepositoryImpl implements MessageRepositoryCtm {
  @Autowired
  private EntityManager em;

  @Override
  public Optional<Message> findOpt(Long id) {

    List<Message> entityList = em.createNamedQuery("Message.findById", Message.class)//
      .setParameter("id", id)//
      .setParameter("deleted", Deleted.PUBLIC.getCode())//
      .getResultList();

    return CollectionUtils.isEmpty(entityList) ? Optional.empty() : Optional.of(entityList.get(0));
  }

  @Override
  public Stream<Message> findStmByRoomId(Integer roomId) {
    List<Message> entityList = em.createNamedQuery("Message.findByRoomId", Message.class)
      .setParameter("roomId", roomId).setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
  }

  @Override
  public ImmutableList<Message> findByRoomId(Integer roomId) {
    List<Message> entityList = em.createNamedQuery("Message.findByRoomId", Message.class)
      .setParameter("roomId", roomId).setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return PsLists.toImt(entityList);
  }

  @Override
  public Stream<Integer> findUserIdStm(Integer roomId) {
    return findStmByRoomId(roomId).map(mEntity -> mEntity.getUserId()).distinct();
  }

  @Override
  public ImmutableList<Integer> findUserId(Integer roomId) {
    return findByRoomId(roomId).collect(mEnt -> mEnt.getUserId()).distinct();
  }

  @Override
  public Stream<Message> findStmByRoomId(Integer roomId, Optional<PageParam> pageOpt) {
    if (!pageOpt.isPresent()) {

      return findStmByRoomId(roomId);
    } else {
      List<Message> entityList = em.createNamedQuery("Message.findByRoomIdLessThanLastId", Message.class) //
        .setParameter("roomId", roomId) //
        .setParameter("deleted", Deleted.PUBLIC.getCode()) //
        .setParameter("lastId", pageOpt.get().getLastId().longValue()) //
        .setMaxResults(pageOpt.get().getLimit()) //
        .getResultList();

      return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
    }
  }

  @Override
  public ImmutableList<Message> findByRoomId(Integer roomId, Optional<PageParam> pageOpt) {
    if (!pageOpt.isPresent()) {

      return findByRoomId(roomId);
    } else {
      List<Message> entityList = em.createNamedQuery("Message.findByRoomIdLessThanLastId", Message.class) //
        .setParameter("roomId", roomId) //
        .setParameter("deleted", Deleted.PUBLIC.getCode()) //
        .setParameter("lastId", pageOpt.get().getLastId().longValue()) //
        .setMaxResults(pageOpt.get().getLimit()) //
        .getResultList();

      return PsLists.toImt(entityList);
    }
  }

  @Override
  public Stream<Message> findStmByUserId(Integer userId) {

    List<Message> entityList = em.createNamedQuery("Message.findByUserId", Message.class)
      .setParameter("roomId", userId).setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
  }

  @Override
  public ImmutableList<Message> findByUserId(Integer userId) {
    List<Message> entityList = em.createNamedQuery("Message.findByUserId", Message.class)
      .setParameter("roomId", userId).setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

    return PsLists.toImt(entityList);
  }

  @Override
  public void logicalDelete(Long id) {
    Optional<Message> entityOpt = findOpt(id);

    if (entityOpt.isPresent()) {
      Message entity = entityOpt.get();
      entity.setDeleted(Deleted.DELETED.getCode());
      em.merge(entity);
    }
  }

}
