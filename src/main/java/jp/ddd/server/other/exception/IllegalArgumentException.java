package jp.ddd.server.other.exception;

public class IllegalArgumentException extends CoreException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * デフォルトコンストラクタ。
   */
  public IllegalArgumentException() {
    super();

  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   */
  public IllegalArgumentException(String message) {
    super(message);
  }

  public IllegalArgumentException(String message, boolean isToUser) {
    super(message, isToUser);

  }

  /**
   * コンストラクタ。
   *
   * @param cause 例外
   */
  public IllegalArgumentException(Throwable cause) {
    super(cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message 例外メッセージ
   * @param cause   例外
   */
  public IllegalArgumentException(Throwable cause, String message) {
    super(message, cause);
  }

  /**
   * コンストラクタ。
   *
   * @param message       例外メッセージ
   * @param cause         例外
   * @param transitionUrl 遷移URL
   */
  public IllegalArgumentException(Throwable cause, String message, String transitionUrl) {
    super(message, cause, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   */
  public IllegalArgumentException(String message, String messageForUser) {
    super(message, messageForUser);

  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param transitionUrl  遷移URL
   */
  public IllegalArgumentException(String message, String messageForUser, String transitionUrl) {
    super(message, messageForUser, transitionUrl);
  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param cause          例外
   */
  public IllegalArgumentException(String message, String messageForUser, Throwable cause) {
    super(message, messageForUser, cause);

  }

  /**
   * コンストラクタ。
   *
   * @param message        例外メッセージ
   * @param messageForUser ユーザ通知用メッセージ
   * @param transitionUrl  遷移URL
   * @param cause          例外
   */
  public IllegalArgumentException(String message, String messageForUser, String transitionUrl, Throwable cause) {
    super(message, messageForUser, transitionUrl, cause);
  }

  @Override
  public InformationLevelType getInformationLevelType() {
    return InformationLevelType.WARN;
  }

}
