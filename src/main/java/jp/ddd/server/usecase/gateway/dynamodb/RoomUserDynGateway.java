package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.table.RoomUserDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface RoomUserDynGateway extends DynamoDBPagingAndSortingRepository<RoomUserDyn, String> {

}
