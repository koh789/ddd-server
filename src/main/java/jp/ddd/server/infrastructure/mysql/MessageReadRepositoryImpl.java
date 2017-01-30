package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.infrastructure.MessageReadRepositoryCtm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by noguchi_kohei 
 */
public class MessageReadRepositoryImpl implements MessageReadRepositoryCtm {
    @Autowired
    private EntityManager em;


}
