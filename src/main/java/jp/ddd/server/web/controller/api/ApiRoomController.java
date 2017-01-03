package jp.ddd.server.web.controller.api;

import javax.servlet.http.HttpServletRequest;

import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.service.RoomService;
import jp.ddd.server.web.form.room.RoomForm;
import jp.ddd.server.web.json.room.RoomJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.utils.Cookies;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.json.room.RegisterRoomJson;

/**
 * メッセージルーム周りを管理します。
 *
 * @author noguchi_kohei
 */
@RestController
@RequestMapping(value = "/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRoomController extends BaseApi {
  @Autowired
  private RoomService roomService;
  @Autowired
  private VisitorManager visitorManager;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public RegisterRoomJson register(@RequestBody @Validated RoomForm form, HttpServletRequest req) {
    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    return new RegisterRoomJson(
      roomService.register(sesUser.getUserId(), form.getRoomName(), form.getRoomUserIdList()));
  }

  /**
   * メッセージ詳細を含まないルーム情報を取得します。
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public RoomJson getRooms(@RequestParam("user_id") Integer userId, HttpServletRequest req) {

    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    if (!userId.equals(sesUser.getUserId())) {
      throw new AccessPermissonException(Msg.FORBIDDEN_ROOM, true);
    }
    return RoomJson.create(roomService.findRoom(userId).toList());
  }
}
