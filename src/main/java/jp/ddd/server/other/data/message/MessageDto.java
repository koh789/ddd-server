package jp.ddd.server.other.data.message;

import jp.ddd.server.other.data.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * メッセージを表現するdtoです。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto implements Dto {
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
