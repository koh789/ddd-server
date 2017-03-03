package jp.ddd.server.domain.model.message;

import jp.ddd.server.domain.model.Entity;
import jp.ddd.server.domain.model.message.core.LastEditDt;
import jp.ddd.server.domain.model.message.core.MessageDt;
import jp.ddd.server.domain.model.message.core.MessageId;
import jp.ddd.server.domain.model.room.core.RoomId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * The persistent class for the message database table.
 */
@Builder
@AllArgsConstructor
@Value
public class Message implements Entity<Message> {
    private static final long serialVersionUID = 5227353660764112526L;

    private final MessageId messageId;

    private final RoomId roomId;

    private final String content;

    private final LastEditDt lastEditDt;

    private final MessageDt messageDt;

    private final Status status;

    private final UserId userId;

    private final ImmutableList<MessageRead> messageReads;

}