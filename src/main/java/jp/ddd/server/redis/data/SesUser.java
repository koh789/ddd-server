package jp.ddd.server.redis.data;

import jp.ddd.server.utils.enums.LoginIdType;
import lombok.*;

import java.io.Serializable;

/**
 * セッションとして扱うユーザ情報です。
 * redisでのキャッシュを想定しています。
 *
 * @author noguchi_kohei
 */
@Builder
@Value
public class SesUser implements Visitor, Serializable {
    private static final long serialVersionUID = 4080397363891314856L;

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

    private boolean isLogin;

    private Integer userId;

    private String loginId;

    private String name;

    private LoginIdType loginIdType;

    private String discription;

    private String eMail;

    private String tel;
}
