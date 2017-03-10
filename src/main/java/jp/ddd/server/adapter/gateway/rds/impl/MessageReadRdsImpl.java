package jp.ddd.server.adapter.gateway.rds.impl;

import jp.ddd.server.adapter.gateway.rds.custom.MessageReadRdsCtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class MessageReadRdsImpl implements MessageReadRdsCtm {
    @Autowired
    private EntityManager em;

}
