package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by noguchi_kohei 
 */
public interface UserDynGateway extends DynamoDBCrudRepository<UserDyn, Integer>, UserDynGatewayCtm {
}
