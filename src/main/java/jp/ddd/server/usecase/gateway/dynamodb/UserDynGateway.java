package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.UserDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface UserDynGateway extends DynamoDBCrudRepository<UserDyn, String>, UserDynGatewayCtm {

}
