package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.repository.redis.entity.SessionUser;
import jp.ddd.server.adapter.repository.redis.SessionUserRepository;
import jp.ddd.server.adapter.repository.mysql.UserRepository;
import jp.ddd.server.other.annotation.NotLoginRequired;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Requests;
import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.data.input.auth.AuthForm;
import jp.ddd.server.adapter.web.data.output.ResultJson;
import jp.ddd.server.adapter.web.data.output.auth.AuthUserJson;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController extends BaseApi {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionUserRepository sessionUserRepository;

    @NotLoginRequired
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultJson<AuthUserJson> register(@RequestBody @Validated AuthForm form, HttpServletRequest req) {

        val sid = Cookies.getKey(req);
        val ipAddress = Requests.getRemoteAddress(req);
        val sesUser = SessionUser
          .login(sessionUserRepository, userRepository, sid, ipAddress, form.getLoginId(), form.getPassword());
        return ResultJson.create(AuthUserJson.create(sesUser));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultJson<String> register(HttpServletRequest req) {
        val sid = Cookies.getKey(req);
        SessionUser.logout(sessionUserRepository, sid);
        return ResultJson.create("OK");
    }
}
