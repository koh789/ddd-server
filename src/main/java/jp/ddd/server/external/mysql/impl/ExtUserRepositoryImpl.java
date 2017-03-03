package jp.ddd.server.external.mysql.impl;

import jp.ddd.server.external.mysql.custom.ExtUserRepositoryCtm;
import jp.ddd.server.external.mysql.entity.ExtUser;
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
public class ExtUserRepositoryImpl implements ExtUserRepositoryCtm {
    @Autowired
    private EntityManager em;

    @Override
    public Optional<ExtUser> getOpt(String loginId) {
        val results = em //
          .createNamedQuery("User.getByLid") //
          .setParameter("lid", (String) loginId) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public Optional<ExtUser> getOpt(String loginId, String hashedPass) {
        val results = em. //
          createNamedQuery("User.getByLidAndPassAndStatus") //
          .setParameter("lid", loginId) //
          .setParameter("pass", hashedPass) //
          .setParameter("status", Status.VALID) //
          .getResultList();
        return DsLists.getFirstOpt(results);
    }

    @Override
    public ImmutableList<ExtUser> find(ImmutableList<Integer> userIds) {
        val results = em //
          .createNamedQuery("User.findByIdsAndStatus")//
          .setParameter("ids", userIds.castToList()) //
          .setParameter("status", Status.VALID)//
          .getResultList();
        return DsLists.toImt(results);
    }
}
