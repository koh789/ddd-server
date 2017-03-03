package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.controller.input.auth.AuthForm;
import jp.ddd.server.adapter.web.presenter.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.output.auth.AuthUserJson;
import jp.ddd.server.domain.model.user.core.HashPass;
import jp.ddd.server.domain.model.user.core.LoginId;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.annotation.NotLoginRequired;
import jp.ddd.server.other.utils.Cookies;
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

    @NotLoginRequired
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultJson<AuthUserJson> register(@RequestBody @Validated AuthForm form, HttpServletRequest req) {

        val sid = Cookies.getKey(req);
        val user = userRepository
          .login(sid, new LoginId(form.getLoginId()), HashPass.createByRawPass(form.getPassword()));
        return ResultJson.create(AuthUserJson.create(sid, user));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultJson<String> register(HttpServletRequest req) {
        val sid = Cookies.getKey(req);
        userRepository.logout(sid);
        return ResultJson.create("OK");
    }
}
