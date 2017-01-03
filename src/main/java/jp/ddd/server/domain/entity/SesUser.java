package jp.ddd.server.domain.entity;

import jp.ddd.server.utils.enums.LoginIdType;
import jp.ddd.server.domain.entity.base.BaseVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * セッションとして扱うユーザ情報です。
 * redisでのキャッシュを想定しています。
 *
 * @author noguchi_kohei
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SesUser extends BaseVisitor {
  private static final long serialVersionUID = 4080397363891314856L;

  private boolean isLogin;

  private Integer userId;

  private String loginId;

  private String name;

  private LoginIdType loginIdType;

  private String discription;

  private String eMail;

  private String tel;
}
