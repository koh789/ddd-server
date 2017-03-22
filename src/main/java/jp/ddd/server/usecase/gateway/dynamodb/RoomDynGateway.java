package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.RoomDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.RoomDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface RoomDynGateway extends DynamoDBPagingAndSortingRepository<RoomDyn, String>, RoomDynGatewayCtm {

}
