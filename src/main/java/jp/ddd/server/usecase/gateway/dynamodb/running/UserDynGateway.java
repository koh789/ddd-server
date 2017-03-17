package jp.ddd.server.usecase.gateway.dynamodb.running;

import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by noguchi_kohei 
 */
public interface UserDynGateway extends JpaRepository<UserDyn, Integer>, UserDynGatewayCtm {
}
