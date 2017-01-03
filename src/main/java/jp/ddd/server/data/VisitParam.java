package jp.ddd.server.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VisitParam extends BaseDto {
  private static final long serialVersionUID = -7292948345099934088L;

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
}
