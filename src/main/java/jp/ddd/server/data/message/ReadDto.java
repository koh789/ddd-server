package jp.ddd.server.data.message;

import jp.ddd.server.data.Dto;
import lombok.Value;

/**
 * 既読情報を表現するdtoです。
 * @author noguchi_kohei
 *
 */
@Value
public class ReadDto implements Dto {
    private static final long serialVersionUID = 1L;

    private final Long messeageId;

    private final Integer readUserId;

    private final String readUserName;

    private final String readLoginId;

    private final String readDate;
}
