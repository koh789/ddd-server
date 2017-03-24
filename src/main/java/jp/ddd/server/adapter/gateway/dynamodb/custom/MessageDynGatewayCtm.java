package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;

/**
 * Created by noguchi_kohei 
 */
public interface MessageDynGatewayCtm {

    MessageDyn saveWithIncrementKey(MessageDyn messageDyn);
}
