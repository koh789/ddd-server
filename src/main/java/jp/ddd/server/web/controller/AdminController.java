package jp.ddd.server.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.ddd.server.annotation.NotLoginRequired;
import jp.ddd.server.domain.entity.SesAdmin;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.service.AdminUserService;
import jp.ddd.server.utils.Cookies;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.form.admin.AdminRegisterForm;
import jp.ddd.server.web.form.admin.LoginForm;

/**
 * 管理関係
 *
 * @author noguchi_kohei
 */
//TODO ハンドリングは後で、認証系インターセプターは後で作る
@Controller
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private AdminUserService adminUserService;
  @Autowired
  private VisitorManager visitorManager;
  @Value("${root.register.flag}")
  private boolean rootRegisterFlag;

  @NotLoginRequired
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String view(Model model) {
    model.addAttribute("loginForm", new LoginForm());
    return "/admin/login";
  }

  @NotLoginRequired
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(Model model, @Validated LoginForm loginForm, BindingResult result, HttpServletRequest req,
                      HttpServletResponse res) {
    if (result.hasErrors()) {
      return "/admin/login";
    }
    try {
      adminUserService.login(Cookies.getKey(req), loginForm.getAdminId(), loginForm.getPassword());
    } catch (IllegalArgumentException | AuthException e) {

      model.addAttribute("error", "ログインに失敗しました。");
      return "/admin/login";
    }

    return "redirect:/admin/mypage";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {

    Cookies.terminate(request, response);
    model.addAttribute("loginForm", new LoginForm());
    return "redirect:/admin/login";
  }

  @NotLoginRequired
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String viewRegister(Model model) {
    model.addAttribute("adminRegisterForm", new AdminRegisterForm());
    return "/admin/register";
  }

  @RequestMapping(value = "/mypage", method = RequestMethod.GET)
  public String viewMypage(Model model) {
    return "/admin/mypage";
  }

  @NotLoginRequired
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(Model model, @Valid AdminRegisterForm adminRegisterForm, BindingResult result,
                         HttpServletRequest req, HttpServletResponse res) {
    if (result.hasErrors()) {
      return "/admin/register";
    }
    AdminRole adminRole = AdminRole.get(adminRegisterForm.getRoleType());
    if (adminRole == AdminRole.SYSTEM_ROOT_ADMIN) {
      if (!rootRegisterFlag) {
        model.addAttribute("error", "登録権限がありません。こちらの登録はルート責任者のみ行えます。");
        return "/admin/register";
      }
    } else {
      String sid = Cookies.getKey(req);
      SesAdmin sesAdmin = visitorManager.getSesAdminOpt(sid)
        .orElseThrow(() -> new AuthException(Msg.NOT_FOUND_USER, true));

      if (sesAdmin.getAdminRole() != AdminRole.SYSTEM_ROOT_ADMIN) {
        model.addAttribute("error", "登録権限がありません。こちらの登録はルート責任者のみ行えます。");
        return "/admin/register";
      }
    }
    if (!adminRegisterForm.getPassword().equals(adminRegisterForm.getConfirmPassword())) {
      model.addAttribute("error", "パスワードが一致しません。");
      return "/admin/register";
    }
    try {
      adminUserService.register(adminRegisterForm.getAdminId(), adminRegisterForm.getPassword(),
        adminRegisterForm.getName(), AdminRole.get(adminRegisterForm.getRoleType()),
        adminRegisterForm.getEMail(), adminRegisterForm.getNote());

    } catch (AccessPermissonException e) {
      model.addAttribute("error", "既に使用されているアカウント名です。");
      return "/admin/register";
    }

    return "/admin/mypage";
  }
}
