package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.Dyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.converter.DateDynamoDbConverter;
import jp.ddd.server.domain.entity.message.Message;
import jp.ddd.server.other.utils.Const;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.val;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Data
@DynamoDBTable(tableName = "message")
public class MessageDyn implements Dyn {
    private static final long serialVersionUID = 8630209538476323532L;

    @DynamoDBHashKey(attributeName = "message_id")
    private Long messageId;

    @DynamoDBAttribute(attributeName = "room_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = Const.IDX_MESSAGE_RID_MAT)
    private Integer roomId;

    @DynamoDBAttribute(attributeName = "message_at")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = Const.IDX_MESSAGE_RID_MAT)
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date messageAt;

    @DynamoDBAttribute(attributeName = "user_id")
    private Integer userId;

    private String content;

    @DynamoDBAttribute(attributeName = "last_edit_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastEditAt;

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private Status status;

    @DynamoDBAttribute(attributeName = "message_reads")
    @DynamoDBTypeConvertedJson()
    private List<MessageReadDyn> messageReads;

    public MessageDyn withMessageId(Long messageId) {
        this.messageId = messageId;
        return this;
    }

    public static MessageDyn create(Message message) {
        val builder = MessageDyn.builder() //
          .roomId(message.getRoomId().getId())//
          .messageAt(message.getMessageAt().getDate())//
          .userId(message.getUserId().getId()).content(message.getContent())//
          .lastEditAt(message.getLastEditAt().getDate())//
          .status(message.getStatus()) //
          .messageReads(Arrays.asList());
        if (message.getMessageId() != null) {
            builder.messageId(message.getMessageId().getId());
        }
        return builder.build();
    }
}
