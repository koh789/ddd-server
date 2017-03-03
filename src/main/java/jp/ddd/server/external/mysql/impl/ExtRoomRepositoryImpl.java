package jp.ddd.server.external.mysql.impl;

import jp.ddd.server.external.mysql.custom.ExtRoomRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtRoom;
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
public class ExtRoomRepositoryImpl implements ExtRoomRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public ImmutableList<ExtRoom> findByDtDesc(Integer userId) {
        val results = em.createNamedQuery("Room.findWithRoomUserByUidDtStatusDesc") //
          .setParameter("uid", userId) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.toImt(results);
    }

    @Override
    public Optional<ExtRoom> getOpt(Integer id) {
        val results = em //
          .createNamedQuery("Room.getOptWithRoomUserByRidStatus")//
          .setParameter("rid", id) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

}

