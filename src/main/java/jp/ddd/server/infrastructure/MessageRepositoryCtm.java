package jp.ddd.server.infrastructure;

import jp.ddd.server.domain.model.message.Message;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Created by noguchi_kohei 
 */
public interface MessageRepositoryCtm {

    ImmutableList<Message> find(Integer roomId);
}
