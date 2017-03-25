package jp.ddd.server.adapter.gateway.dynamodb.custom;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.other.data.common.IdPage;
import org.eclipse.collections.api.list.ImmutableList;

import java.util.Optional;

/**
 * Created by noguchi_kohei 
 */
public interface MessageDynGatewayCtm {

    MessageDyn saveWithIncrementKey(MessageDyn messageDyn);

    ImmutableList<MessageDyn> findDesc(Integer roomId, IdPage idPage);
}
