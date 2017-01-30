package jp.ddd.server.application;

import jp.ddd.server.domain.model.user.SessionUser;

/**
 * Created by noguchi_kohei 
 */
public interface AuthService {

    SessionUser login(String sid, String ipAddress, String loginId, String pass);
}
