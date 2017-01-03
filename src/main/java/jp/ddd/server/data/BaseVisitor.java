package jp.ddd.server.data;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザー情報を表現する基底クラスです。
 *
 * @author noguchi_kohei
 */
@Data
public abstract class BaseVisitor implements Visitor, Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 利用者を特定するID
   */
  private String sessionId;
  /**
   * 利用者のIPアドレス
   */
  private String ipAddress;
  /**
   * 利用者のユーザーエージェント
   */
  private String userAgent;
  /**
   * 利用者のリファラー
   */
  private String referer;

  private boolean isLoginUser = false;

  private boolean isLoginAdmin = false;

}
