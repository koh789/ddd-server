package jp.ddd.server.adapter.gateway.dynamodb.custom;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

/**
 * Created by noguchi_kohei 
 */
public interface SequenceDynGatewayCtm {

    Integer incrementNum(String tableName);
}
