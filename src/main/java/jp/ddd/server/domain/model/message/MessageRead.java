package jp.ddd.server.domain.model.message;

import jp.ddd.server.domain.model.message.core.MessageId;
import jp.ddd.server.domain.model.message.core.MessageReadId;
import jp.ddd.server.domain.model.message.core.ReadDt;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.external.mysql.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class MessageRead extends BaseEntity {
    private static final long serialVersionUID = 2377932200497420692L;

    private final MessageReadId messageReadId;

    private final ReadDt readDt;

    private final MessageId messageId;

    private final UserId userId;

}