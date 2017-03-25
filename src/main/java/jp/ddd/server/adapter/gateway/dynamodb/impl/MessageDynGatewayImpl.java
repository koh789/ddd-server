package jp.ddd.server.adapter.gateway.dynamodb.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import jp.ddd.server.adapter.gateway.dynamodb.custom.MessageDynGatewayCtm;
import jp.ddd.server.adapter.gateway.dynamodb.impl.base.DynamoDbClient;
import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.other.data.common.IdPage;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.DsLists;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
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
        val item = messageDyn.withMessageId(id);
        dynDynamoDbClient.getMapper().save(item);
        return item;
    }

    @Override
    public ImmutableList<MessageDyn> findDesc(Integer roomId, IdPage idPage) {

        DynamoDBQueryExpression expression = new DynamoDBQueryExpression<MessageDyn>();//
        expression //
          .withKeyConditionExpression("room_id=:rid")
          .addExpressionAttributeValuesEntry(":rid", new AttributeValue().withN(String.valueOf(roomId)));

        idPage.getLastIdOpt().ifPresent(mid -> {
            expression //
              .withKeyConditionExpression(expression.getKeyConditionExpression() + " AND message_id<:mid") //
              .addExpressionAttributeValuesEntry(":mid", new AttributeValue().withN(String.valueOf(mid)));
        });
        expression //
          .withIndexName(Const.IDX_MESSAGE_RID_MID) //
          .withConsistentRead(false) //
          .withScanIndexForward(false) //
          .withLimit(idPage.getLimit());

        QueryResultPage<MessageDyn> results = dynDynamoDbClient.getMapper().queryPage(MessageDyn.class, expression);
        return DsLists.toImt(results.getResults());
    }
}
