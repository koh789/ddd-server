package jp.ddd.server.domain.repository.message.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.ddd.server.domain.entity.MessageRead;
import jp.ddd.server.domain.repository.message.MessageReadRepositoryCtm;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.utils.Dates;
import jp.ddd.server.utils.PsLists;

@Repository
public class MessageReadRepositoryImpl implements MessageReadRepositoryCtm {
    @Autowired
    private EntityManager em;

    /**
     * メッセージに紐づく既読stmを取得します。
     */
    @Override
    public Stream<MessageRead> findStm(Long messageId) {
        List<MessageRead> entityList = em//
          .createNamedQuery("MessageRead.findByMsgId", MessageRead.class)//
          .setParameter("messageId", messageId)//
          .setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();

        return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
    }

    @Override
    public ImmutableList<MessageRead> find(Long messageId) {
        List<MessageRead> entityList = em//
          .createNamedQuery("MessageRead.findByMsgId", MessageRead.class)//
          .setParameter("messageId", messageId)//
          .setParameter("deleted", Deleted.PUBLIC.getCode()).getResultList();
        return PsLists.toImt(entityList);
    }

    @Override
    public Stream<MessageRead> findStmByUnique(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted) {
        TypedQuery<MessageRead> query = em //
          .createNamedQuery("MessageRead.findByMsgListAndUserId", MessageRead.class)//
          .setParameter("messageIdList", messageIdList) //
          .setParameter("userId", readUserId);

        if (isContainDeleted) {
            List<Byte> deletedList = Arrays.asList(Deleted.values()).stream().map(d -> d.getCode())
              .collect(Collectors.toList());
            query.setParameter("deletedList", deletedList);
        } else {
            query.setParameter("deletedList", Arrays.asList(new Byte[] { Deleted.PUBLIC.getCode() }));
        }
        List<MessageRead> entityList = query.getResultList();

        return CollectionUtils.isEmpty(entityList) ? Stream.empty() : entityList.stream();
    }

    @Override
    public ImmutableList<MessageRead> findByUnique(List<Long> messageIdList, Integer readUserId,
      boolean isContainDeleted) {
        TypedQuery<MessageRead> query = em //
          .createNamedQuery("MessageRead.findByMsgListAndUserId", MessageRead.class)//
          .setParameter("messageIdList", messageIdList) //
          .setParameter("userId", readUserId);

        if (isContainDeleted) {
            List<Byte> deletedList = Arrays.asList(Deleted.values()).stream().map(d -> d.getCode())
              .collect(Collectors.toList());
            query.setParameter("deletedList", deletedList);
        } else {
            query.setParameter("deletedList", Arrays.asList(new Byte[] { Deleted.PUBLIC.getCode() }));
        }
        List<MessageRead> entityList = query.getResultList();
        return PsLists.toImt(entityList);
    }

    @Override
    public Optional<MessageRead> findOptByUnique(Long messageId, Integer readUserId, boolean isContainDeleted) {
        TypedQuery<MessageRead> query = em //
          .createNamedQuery("MessageRead.findByUnique", MessageRead.class)//
          .setParameter("messageId", messageId) //
          .setParameter("userId", readUserId);

        if (isContainDeleted) {
            List<Byte> deletedList = Arrays.asList(Deleted.values()).stream().map(d -> d.getCode())
              .collect(Collectors.toList());
            query.setParameter("deletedList", deletedList);
        } else {
            query.setParameter("deletedList", Arrays.asList(new Byte[] { Deleted.PUBLIC.getCode() }));
        }
        List<MessageRead> entityList = query.getResultList();
        return CollectionUtils.isEmpty(entityList) ? Optional.empty() : Optional.of(entityList.get(0));
    }

    @Override
    @Transactional
    public MessageRead saveByUnique(Long messageId, Integer readUserId, boolean isContainDeleted) {

        Optional<MessageRead> savedEntityOpt = findOptByUnique(messageId, readUserId, true);
        if (savedEntityOpt.isPresent()) {
            return em.merge(savedEntityOpt.get());
        } else {
            MessageRead newEntity = new MessageRead(0, Dates.now(), messageId, readUserId);
            em.persist(newEntity);
            return newEntity;
        }
    }

    @Override
    @Transactional
    public Stream<MessageRead> saveByUnique(List<Long> messageIdList, Integer readUserId, boolean isContainDeleted) {
        return messageIdList.stream().map(mid -> {
            Optional<MessageRead> savedEntityOpt = findOptByUnique(mid, readUserId, true);
            if (savedEntityOpt.isPresent()) {
                return em.merge(savedEntityOpt.get());
            } else {
                MessageRead newEntity = new MessageRead(0, Dates.now(), mid, readUserId);
                em.persist(newEntity);
                return newEntity;
            }
        });
    }

    @Override
    @Transactional
    public ImmutableList<MessageRead> saveByUniques(List<Long> messageIdList, Integer readUserId,
      boolean isContainDeleted) {
        return PsLists.toImt(messageIdList).collect(mid -> {
            Optional<MessageRead> savedEntityOpt = findOptByUnique(mid, readUserId, true);
            if (savedEntityOpt.isPresent()) {
                return em.merge(savedEntityOpt.get());
            } else {
                MessageRead newEntity = new MessageRead(0, Dates.now(), mid, readUserId);
                em.persist(newEntity);
                return newEntity;
            }
        });
    }

    @Override
    public void logicalDelete(Long messageId) {
        findStm(messageId).forEach(mrEnt -> {
            mrEnt.setDeleted(Deleted.DELETED.getCode());
            em.merge(mrEnt);
        });
    }
}
