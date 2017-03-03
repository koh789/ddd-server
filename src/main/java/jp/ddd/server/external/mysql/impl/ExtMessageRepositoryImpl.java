package jp.ddd.server.external.mysql.impl;

import jp.ddd.server.external.mysql.custom.ExtMessageRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtMessage;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class ExtMessageRepositoryImpl implements ExtMessageRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<ExtMessage> findByRoomId(Integer roomId, Page page) {

        val results = em //
          .createNamedQuery("Message.findWithReadByRoomIdOrderByIdDesc")//
          .setParameter("rid", (Integer) roomId)//
          .setFirstResult(page.getOffset())//
          .setMaxResults(page.getLimit()) //
          .getResultList();
        return DsLists.toImt(results);
    }

    @Override
    public ImmutableList<ExtMessage> findUnread(Integer roomId, Integer userId) {
        val results = em.createNamedQuery("Message.findUnreadByRidAndUid")//
          .setParameter("rid", roomId)//
          .setParameter("uid", userId)//
          .getResultList();
        return DsLists.toImt(results);
    }
}
