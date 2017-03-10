package jp.ddd.server.usecase.repository.impl;

import jp.ddd.server.usecase.gateway.rds.MessageReadRds;
import jp.ddd.server.usecase.gateway.rds.MessageRds;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.domain.entity.room.core.RoomId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.adapter.gateway.rds.entity.MessageExt;
import jp.ddd.server.adapter.gateway.rds.entity.MessageReadExt;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.Dates;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * {@link MessageRepository}実装クラスです。
 * 当クラスでDDDにおけるドメインレポジトリを表現します。
 * Created by noguchi_kohei
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private MessageRds messageRds;
    @Autowired
    private MessageReadRds messageReadRds;

    @Override
    public Message register(Message message) {
        val result = messageRds.save(MessageExt.create(message));
        return Message.create(result);
    }

    @Override
    public ImmutableList<Message> findAndSaveRead(RoomId roomId, UserId userId, Page page) {
        messageRds.findUnread(roomId.getId(), userId.getId())
          .each(m -> messageReadRds.save(MessageReadExt.create(m.getId(), userId.getId(), Dates.now())));
        return messageRds.findByRoomId(roomId.getId(), page).collect(m -> Message.create(m));
    }
}
