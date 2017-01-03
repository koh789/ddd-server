package jp.ddd.server.data.message;

import jp.ddd.server.data.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 既読情報を表現するdtoです。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadDto extends BaseDto {
  private static final long serialVersionUID = 1L;

  private Long messeageId;

  private Integer readUserId;

  private String readUserName;

  private String readLoginId;

  private String readDate;
}
