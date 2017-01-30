package jp.ddd.server.infrastructure.mysql;

import jp.ddd.server.infrastructure.MessageReadRepositoryCtm;
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
