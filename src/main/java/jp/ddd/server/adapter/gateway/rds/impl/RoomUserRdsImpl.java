package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.adapter.gateway.rds.custom.RoomUserRdsCtm;
import jp.ddd.server.adapter.gateway.rds.entity.RoomUserExt;
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
public class RoomUserRdsImpl implements RoomUserRdsCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<RoomUserExt> getOptByUnq(Integer roomId, Integer userId) {
        val results = em //
          .createNamedQuery("RoomUser.getByUnq")//
          .setParameter("rid", roomId) //
          .setParameter("uid", userId) //
          .getResultList();

        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<RoomUserExt> findByRoomId(Integer roomId) {
        val results = em //
          .createNamedQuery("RoomUser.getByRoomIdAndStatus")//
          .setParameter("rid", roomId) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.toImt(results);
    }
}

