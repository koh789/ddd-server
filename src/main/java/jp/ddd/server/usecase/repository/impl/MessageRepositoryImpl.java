package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.usecase.gateway.dynamodb.MessageDynGateway;
import jp.ddd.server.usecase.gateway.rds.MessageRdsGateway;
import jp.ddd.server.usecase.gateway.rds.MessageReadRdsGateway;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * {@link MessageRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Component
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private MessageRdsGateway messageRdsGateway;
    @Autowired
    private MessageDynGateway messageDynGateway;
    @Autowired
    private MessageReadRdsGateway messageReadRdsGateway;

    @Override
    public Message register(Message message) {
        val result = messageDynGateway.saveWithIncrementKey(MessageDyn.create(message));
        return Message.create(result);
    }

    //    @Override
    //    public ImmutableList<Message> findAndSaveRead(RoomId roomId, UserId userId, Page page) {
    //        messageRdsGateway.findUnread(roomId.getUserId(), userId.getUserId())
    //          .each(m -> messageReadRdsGateway.save(MessageReadRds.create(m.getUserId(), userId.getUserId(), Dates.now())));
    //        return messageRdsGateway.findByRoomId(roomId.getUserId(), page).collect(m -> Message.create(m));
    //    }
}
