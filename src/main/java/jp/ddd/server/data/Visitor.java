package jp.ddd.server.data;

/**
 * 訪問者を表現するマーカーインターフェースです。
 *
 * @author noguchi_kohei
 */
public interface Visitor {

  /**
   * sessionIdを取得します。
   */
  String getSessionId();

  /**
   * アクターのIPアドレスを表現する文字列を返します。
   *
   * @return IPアドレスを表現する文字列
   */
  String getIpAddress();

  /**
   * アクターのUserAgentを表現する文字列を返します。
   *
   * @return UserAgent
   */
  String getUserAgent();

  /**
   * アクターのリファラーを取得します。
   *
   * @return アクターのリファラー
   */
  String getReferer();

  boolean isLoginUser();

  boolean isLoginAdmin();
}
