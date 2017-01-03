package jp.ddd.server.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ddd.server.data.message.DelMessageDto;
import jp.ddd.server.data.message.SaveMessageDto;
import jp.ddd.server.domain.entity.Message;
import jp.ddd.server.manager.MessageManager;
import jp.ddd.server.service.MessageService;

/**
 * メッセージ関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Service
public class MessageServiceImpl implements MessageService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private MessageManager messageManager;

  @Override
  public SaveMessageDto register(Integer roomId, Integer userId, String content) {
    return messageManager.register(roomId, userId, content).orElse(new SaveMessageDto());
  }

  @Override
  public boolean isMessageUser(Integer userId, Long messageId) {
    Optional<Message> matchedMessageOpt = messageManager.get(messageId).filter(m -> userId.equals(m.getUserId()));

    return matchedMessageOpt.isPresent() ? true : false;
  }

  @Override
  public DelMessageDto delete(Integer roomId, Integer userId, Long messageId) {
    return messageManager.delete(roomId, userId, messageId);
  }
}
