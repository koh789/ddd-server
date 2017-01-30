package jp.ddd.server.web.controller.api;

import jp.ddd.server.application.AuthService;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Requests;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.data.form.auth.AuthForm;
import jp.ddd.server.web.data.json.ResultJson;
import jp.ddd.server.web.data.json.auth.AuthUserJson;
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
public class AuthrController extends BaseApi {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultJson<AuthUserJson> register(@RequestBody @Validated AuthForm form, HttpServletRequest req) {

        val sesUser = authService
          .login(Cookies.getKey(req), Requests.getRemoteAddress(req), form.getLoginId(), form.getPassword());
        return new ResultJson<AuthUserJson>(AuthUserJson.create(sesUser));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResultJson<String> register(HttpServletRequest req) {

        return new ResultJson<String>("OK");
    }
}
