package jp.ddd.server.domain.model.user;

import jp.ddd.server.domain.repository.SessionUserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@AllArgsConstructor
@Value
public class SessionUser implements Serializable {
    private static final long serialVersionUID = -324299695194671919L;

    /**
     * 利用者を特定するID
     */
    private final String sessionId;
    /**
     * 利用者のIPアドレス
     */
    private final String ipAddress;

    private final Integer userId;

    private final String loginId;

    private final String name;

    private final String email;

    private final String tel;

    public static SessionUser create(String sid, String ipAddress, User user) {
        return SessionUser.builder().sessionId(sid).ipAddress(ipAddress).userId(user.getId()).loginId(user.getLoginId())
          .name(user.getName()).email(user.getEmail()).tel(user.getTel()).build();
    }

    public static SessionUser login(SessionUserRepository rep, String sid, String ipAddress, User user) {
        return rep.save(create(sid, ipAddress, user));
    }

    public static boolean isLogin(SessionUserRepository rep, String sid) {
        return rep.getOpt(sid).isPresent();
    }
}
