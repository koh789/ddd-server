package jp.ddd.server.data.user;

import jp.ddd.server.utils.enums.LoginIdType;
import jp.ddd.server.data.BaseVisitor;
import lombok.Data;

@Data
public class UserDto extends BaseVisitor {

  private Integer userId;

  private String loginId;

  private String name;

  private LoginIdType loginIdType;

  private String discription;

  private String eMail;

  private String tel;
}
