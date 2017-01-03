package jp.ddd.server.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ddd.server.annotation.NotLoginRequired;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.exception.NotFoundException;
import jp.ddd.server.service.UserService;
import jp.ddd.server.utils.Cookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.controller.base.BaseController;
import jp.ddd.server.web.form.auth.LoginForm;

/**
 * ユーザの認証を管理します。
 *
 * @author noguchi_kohei
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
  @Autowired
  private UserService userService;
  @Autowired
  private VisitorManager visitorManager;

  @NotLoginRequired
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String view(Model model) {
    model.addAttribute("loginForm", new LoginForm());
    return "/auth/login";
  }

  @NotLoginRequired
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(Model model, @Validated LoginForm loginForm, BindingResult result, HttpServletRequest req,
                      HttpServletResponse res) {
    if (result.hasErrors()) {
      return "/auth/login";
    }
    try {
      String sid = Cookies.getKey(req);
      userService.login(sid, loginForm.getLoginId(), loginForm.getPassword());

    } catch (NotFoundException | AuthException e) {

      model.addAttribute("error", Msg.FAIL_LOGIN);
      return "/auth/login";
    }

    return "redirect:/user/mypage";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(Model model, HttpServletRequest req, HttpServletResponse res) {
    Cookies.terminate(req, res);
    visitorManager.logoutUser(Cookies.getKey(req));
    model.addAttribute("loginForm", new LoginForm());
    return "redirect:/auth/login";
  }
}
