package jp.ddd.server.adapter.repository.mysql.impl;

import jp.ddd.server.adapter.repository.mysql.MessageReadRepositoryCtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class MessageReadRepositoryImpl implements MessageReadRepositoryCtm {
    @Autowired
    private EntityManager em;

}
