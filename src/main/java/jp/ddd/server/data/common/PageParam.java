package jp.ddd.server.data.common;

import jp.ddd.server.data.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageParam extends BaseDto {

  private static final long serialVersionUID = 1L;

  private Integer lastId;

  private int limit;

}
