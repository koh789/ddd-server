package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.infrastructure.MessageRepositoryCtm;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
public class MessageRepositoryImpl implements MessageRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<Message> findByRoomId(Integer roomId) {
        val results = em.createNamedQuery("Message.findByRoomId").setParameter("rid", (Integer) roomId).getResultList();
        return DsLists.toImt(results);
    }
}
