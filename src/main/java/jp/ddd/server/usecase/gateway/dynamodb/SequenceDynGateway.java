package jp.ddd.server.usecase.gateway.dynamodb;

import jp.ddd.server.adapter.gateway.dynamodb.custom.SequenceDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.table.SequenceDyn;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

/**
 * Created by noguchi_kohei 
 */
@EnableScan
public interface SequenceDynGateway
  extends DynamoDBPagingAndSortingRepository<SequenceDyn, String>, SequenceDynGatewayCtm {

}
