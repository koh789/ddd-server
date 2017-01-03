package jp.ddd.server.service;

import jp.ddd.server.data.message.DelMessageDto;
import jp.ddd.server.data.message.SaveMessageDto;

public interface MessageService {

  SaveMessageDto register(Integer roomId, Integer userId, String content);

  boolean isMessageUser(Integer userId, Long messageId);

  DelMessageDto delete(Integer roomId, Integer userId, Long messageId);
}
