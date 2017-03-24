package jp.ddd.server.adapter.gateway.dynamodb.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.base.BaseDyn;
import jp.ddd.server.adapter.gateway.dynamodb.table.converter.DateDynamoDbConverter;
import jp.ddd.server.domain.entity.message.Message;
import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "message")
public class MessageDyn implements BaseDyn {
    private static final long serialVersionUID = 8630209538476323532L;

    @DynamoDBHashKey(attributeName = "message_id")
    private Long messageId;

    @DynamoDBAttribute(attributeName = "room_id")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_rid_mat")
    private Integer roomId;

    @DynamoDBAttribute(attributeName = "message_at")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "idx_rid_mat")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date messageAt;

    @DynamoDBAttribute(attributeName = "user_id")
    private Integer userId;

    private String content;

    @DynamoDBAttribute(attributeName = "last_edit_at")
    @DynamoDBTypeConverted(converter = DateDynamoDbConverter.class)
    private Date lastEditAt;

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
          .messageReads(Arrays.asList());
        if (message.getMessageId() != null) {
            builder.messageId(message.getMessageId().getId());
        }
        return builder.build();
    }
}
