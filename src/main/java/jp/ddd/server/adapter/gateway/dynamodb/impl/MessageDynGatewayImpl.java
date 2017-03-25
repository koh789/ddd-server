package jp.ddd.server.adapter.gateway.dynamodb.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import jp.ddd.server.adapter.gateway.dynamodb.custom.MessageDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.other.data.common.IdPage;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
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

    @Override
    public ImmutableList<MessageDyn> findDesc(Integer roomId, IdPage idPage) {
        MutableMap attributeMap = Maps.mutable.of(":rid", new AttributeValue(String.valueOf(roomId)));
        DynamoDBScanExpression expression = new DynamoDBScanExpression()//
          .withIndexName(Const.IDX_MESSAGE_RID_MAT) //
          .withConsistentRead(false)//
          .withFilterExpression("room_id=:rid") //
          .withExpressionAttributeValues(attributeMap)//
          .withLimit(idPage.getLimit());

        idPage.getLastIdOpt().ifPresent(mid -> {
            String addExpression = " AND messageId<:mid";
            expression.setFilterExpression(expression.getFilterExpression() + addExpression);
            expression.getExpressionAttributeValues().put(":mid", new AttributeValue(String.valueOf(mid)));
        });
        val results = dynDynamoDbClient.getMapper()
          .scan(MessageDyn.class, expression, dynDynamoDbClient.getMapperConfig());
        return DsLists.toImt(results.iterator());
    }
}
