package jp.ddd.server.adapter.gateway.dynamodb.impl;

import jp.ddd.server.adapter.gateway.dynamodb.custom.MessageDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by noguchi_kohei 
 */
@Repository
public class MessageDynGatewayImpl implements MessageDynGatewayCtm {
    @Autowired
    private DynamoDbClient<MessageDyn> dynDynamoDbClient;

    @Override
    public MessageDyn saveWithIncrementKey(MessageDyn messageDyn) {
        val id = dynDynamoDbClient.incrementLongNum(MessageDyn.class);
        return dynDynamoDbClient.save(messageDyn.withMessageId(id));
    }
}
