package jp.ddd.server.adapter.gateway.impl;

import jp.ddd.server.domain.repository.MessageRepository;
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
}
