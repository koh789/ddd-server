package jp.ddd.server.web.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddd.server.annotation.NotLoginRequired;
import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.service.UserService;
import jp.ddd.server.utils.Cookies;
import jp.ddd.server.web.form.user.UserForm;
import jp.ddd.server.web.json.user.RegisterUserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.ddd.server.utils.JsonFuncs;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.json.user.UserJson;

/**
 * user関係のcontrollerです。
 *
 * @author noguchi_kohei
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiUserController extends BaseApi {
  @Autowired
  private UserService userService;
  @Autowired
  private VisitorManager visitorManager;

  @NotLoginRequired
  @RequestMapping(value = "", method = RequestMethod.POST)
  public RegisterUserJson register(@RequestBody @Validated UserForm userForm, HttpServletRequest req) {
    return new RegisterUserJson((userService.register(userForm)));
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public UserJson get(@PathVariable Integer userId, HttpServletRequest req, HttpServletResponse res) {

    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    if (!sesUser.getUserId().equals(userId)) {
      throw new AccessPermissonException(Msg.FORBIDDEN, true);
    }
    return JsonFuncs.TO_USER_JSON.apply(userService.get(userId));
  }
}
