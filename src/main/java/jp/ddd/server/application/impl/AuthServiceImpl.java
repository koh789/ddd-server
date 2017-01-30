package jp.ddd.server.application.impl;

import jp.ddd.server.application.AuthService;
import jp.ddd.server.domain.model.user.SessionUser;
import jp.ddd.server.domain.model.user.User;
import jp.ddd.server.domain.repository.SessionUserRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by noguchi_kohei 
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionUserRepository sessionUserRepository;

    @Override
    public SessionUser login(String sid, String ipAddress, String loginId, String pass) {
        return User.getOpt(userRepository, loginId, pass) //
          .map(u -> SessionUser.login(sessionUserRepository, sid, ipAddress, u)) //
          .orElseThrow(() -> new NotFoundException("invalid loginId and password!" + loginId));
    }
}
