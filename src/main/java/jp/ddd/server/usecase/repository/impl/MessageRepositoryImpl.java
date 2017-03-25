package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.usecase.gateway.dynamodb.MessageDynGateway;
import jp.ddd.server.usecase.gateway.rds.MessageRdsGateway;
import jp.ddd.server.usecase.gateway.rds.MessageReadRdsGateway;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
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
    private MessageDynGateway messageDynGateway;

    @Override
    public Message register(Message message) {
        val result = messageDynGateway.saveWithIncrementKey(MessageDyn.create(message));
        return Message.create(result);
    }

    /**
     * メッセージを読み込みます。
     * 読み込んだメッセージ情報に応じて既読情報も更新します。
     * @param roomId
     * @param userId
     * @param page
     * @return
     */
    public ImmutableList<Message> load(RoomId roomId, UserId userId, Page page) {

        //TODO
        return Lists.immutable.empty();
    }

    //    @Override
    //    public ImmutableList<Message> findAndSaveRead(RoomId roomId, UserId userId, Page page) {
    //        messageRdsGateway.findUnread(roomId.getUserId(), userId.getUserId())
    //          .each(m -> messageReadRdsGateway.save(MessageReadRds.create(m.getUserId(), userId.getUserId(), Dates.now())));
    //        return messageRdsGateway.findByRoomId(roomId.getUserId(), page).collect(m -> Message.create(m));
    //    }
}
