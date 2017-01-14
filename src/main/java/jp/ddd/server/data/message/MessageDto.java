package jp.ddd.server.data.message;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.data.BaseDto;
import jp.ddd.server.utils.Dates;
import org.eclipse.collections.api.list.ImmutableList;

import jp.ddd.server.data.user.UserDto;
import jp.ddd.server.domain.entity.MessageRead;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メッセージを表現するdtoです。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    private Integer roomId;

    private Long messageId;

    private Integer messageUserId;

    private String messageUserName;

    private String messageLoginId;

    private String content;

    private Date messageDate;

    private List<ReadDto> readList;

}
