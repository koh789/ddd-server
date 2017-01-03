package jp.ddd.server.domain.entity;

import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.data.BaseVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * セッションとして扱う管理者情報です。
 * redisでのキャッシュを想定しています。
 *
 * @author noguchi_kohei
 */
@AllArgsConstructor
@Data
public class SesAdmin extends BaseVisitor {
  private static final long serialVersionUID = -511850516262840821L;

  private Integer id;

  private String adminId;

  private String name;

  private AdminRole adminRole;

  private String eMail;

  private String note;
}
