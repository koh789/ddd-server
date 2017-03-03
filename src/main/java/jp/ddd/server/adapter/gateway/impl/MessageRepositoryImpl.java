package jp.ddd.server.adapter.gateway.impl;

import jp.ddd.server.adapter.gateway.external.rdb.ExtMessageReadRepository;
import jp.ddd.server.adapter.gateway.external.rdb.ExtMessageRepository;
import jp.ddd.server.domain.model.message.Message;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.external.mysql.entity.ExtMessage;
import jp.ddd.server.external.mysql.entity.ExtMessageRead;
import jp.ddd.server.other.data.common.Page;
import jp.ddd.server.other.utils.Dates;
import lombok.val;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * {@link MessageRepository}実装クラスです。
 * 当クラスでドメインモデルと外部プロトコル上のオブジェクトとの
 * インピーダンスミスマッチの解決を集約します。
 * Created by noguchi_kohei
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private ExtMessageRepository extMessageRepository;
    @Autowired
    private ExtMessageReadRepository extMessageReadRepository;

    @Override
    public Message register(Message message) {
        val result = extMessageRepository.save(ExtMessage.create(message));
        return Message.create(result);
    }

    @Override
    public ImmutableList<Message> findAndSaveRead(RoomId roomId, UserId userId, Page page) {
        extMessageRepository.findUnread(roomId.getId(), userId.getId())
          .each(m -> extMessageReadRepository.save(ExtMessageRead.create(m.getId(), userId.getId(), Dates.now())));
        return extMessageRepository.findByRoomId(roomId.getId(), page).collect(m -> Message.create(m));
    }
}
