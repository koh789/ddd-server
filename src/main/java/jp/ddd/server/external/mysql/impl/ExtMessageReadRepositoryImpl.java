package jp.ddd.server.external.mysql.impl;

import jp.ddd.server.external.mysql.custom.ExtMessageReadRepositoryCtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class ExtMessageReadRepositoryImpl implements ExtMessageReadRepositoryCtm {
    @Autowired
    private EntityManager em;

}
