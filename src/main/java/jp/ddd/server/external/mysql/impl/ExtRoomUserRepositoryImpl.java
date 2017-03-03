package jp.ddd.server.external.mysql.impl;

import jp.ddd.server.external.mysql.custom.ExtRoomUserRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtRoomUser;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.other.utils.enums.Status;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class ExtRoomUserRepositoryImpl implements ExtRoomUserRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<ExtRoomUser> getOptByUnq(Integer roomId, Integer userId) {
        val results = em //
          .createNamedQuery("RoomUser.getByUnq")//
          .setParameter("rid", roomId) //
          .setParameter("uid", userId) //
          .getResultList();

        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<ExtRoomUser> findByRoomId(Integer roomId) {
        val results = em //
          .createNamedQuery("RoomUser.getByRoomId")//
          .setParameter("rid", roomId) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.toImt(results);
    }
}

