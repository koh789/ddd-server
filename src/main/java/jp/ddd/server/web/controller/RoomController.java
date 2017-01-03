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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rooms")
public class RoomController extends BaseController {

  @Autowired
  private VisitorManager visitorManager;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String viewMypage(@RequestParam("userId") Integer userId, Model model, HttpServletRequest req) {
    Optional<SesUser> sesUserOpt = visitorManager.getSesUserOpt(Cookies.getKey(req));

    if (!sesUserOpt.isPresent() || !sesUserOpt.get().isLogin()) {
      return "redirect:/auth/login";
    }
    if (!userId.equals(sesUserOpt.get().getUserId())) {
      return "redirect:errors/403.html";
    }
    model.addAttribute("userId", userId);
    return "/rooms/index";

  }

}
