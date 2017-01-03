package jp.ddd.server.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.utils.Cookies;
import jp.ddd.server.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
  @Autowired
  private VisitorManager visitorManager;

  @RequestMapping(value = "/mypage", method = RequestMethod.GET)
  public String viewMypage(Model model, HttpServletRequest req) {
    Optional<SesUser> sesUserOpt = visitorManager.getSesUserOpt(Cookies.getKey(req));
    if (sesUserOpt.isPresent()) {
      model.addAttribute("userId", sesUserOpt.get().getUserId());
      return "/user/mypage";
    } else {
      return "redirect:/auth/login";
    }

  }
}
