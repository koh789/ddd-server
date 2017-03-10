package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.adapter.gateway.rds.custom.UserRdsCtm;
import jp.ddd.server.adapter.gateway.rds.entity.UserExt;
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
public class UserRdsImpl implements UserRdsCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<UserExt> getOpt(String loginId) {
        val results = em //
          .createNamedQuery("User.getByLid") //
          .setParameter("lid", (String) loginId) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public Optional<UserExt> getOpt(String loginId, String hashedPass) {
        val results = em. //
          createNamedQuery("User.getByLidAndPassAndStatus") //
          .setParameter("lid", loginId) //
          .setParameter("pass", hashedPass) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<UserExt> find(ImmutableList<Integer> userIds) {
        val results = em //
          .createNamedQuery("User.findByIdsAndStatus")//
          .setParameter("ids", userIds.castToList()) //
          .setParameter("status", Status.VALID)//
          .getResultList();
        return DsLists.toImt(results);
    }
}
