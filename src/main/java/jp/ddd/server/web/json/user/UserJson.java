package jp.ddd.server.web.json.user;

import jp.ddd.server.web.json.Json;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserJson implements Json {
  private static final long serialVersionUID = 1L;

  private Integer userId;

  private String loginId;

  private String name;

  private String loginIdType;

  private String discription;

  private String eMail;

  private String tel;

}
