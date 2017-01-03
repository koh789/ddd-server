package jp.ddd.server.web.controller.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import jp.ddd.server.data.room.RoomWithUsersMsgDto;
import jp.ddd.server.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.ddd.server.data.common.PageParam;
import jp.ddd.server.data.message.SaveMessageDto;
import jp.ddd.server.domain.entity.SesUser;
import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.manager.MessageManager;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.service.MessageService;
import jp.ddd.server.service.RoomService;
import jp.ddd.server.utils.Cookies;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.form.message.MessageForm;
import jp.ddd.server.web.json.message.DelMessageJson;
import jp.ddd.server.web.json.message.PostMessageJson;
import jp.ddd.server.web.json.message.RoomMessageJson;

/**
 * ルームメッセージを管理するcontrollerです。
 *
 * @author noguchi_kohei
 */
@RestController
@RequestMapping(value = "/api/v1/rooms/{roomId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiMessageController extends BaseApi {
  @Autowired
  private RoomService roomService;
  @Autowired
  private MessageService messageService;
  @Autowired
  private MessageManager messageManager;
  @Autowired
  private VisitorManager visitorManager;

  /**
   * ルームのメッセージ一覧をを取得します。
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public RoomMessageJson get(@PathVariable Integer roomId,
                             @RequestParam(value = "lastId", required = false) Optional<Integer> lastIdOpt,
                             @RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest req) {

    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    if (!roomService.isRoomUser(sesUser.getUserId(), roomId)) {
      throw new AccessPermissonException(Msg.FORBIDDEN_ROOM, true);
    }

    Optional<PageParam> pageOpt = lastIdOpt.map(lastId -> new PageParam(lastId, limit));
    Optional<RoomWithUsersMsgDto> roomWithUsersMsgOpt = roomService.getRoomWithUsersMsgOpt(sesUser.getUserId(),
      roomId, pageOpt);

    if (roomWithUsersMsgOpt.isPresent()) {

      return RoomMessageJson.create(roomWithUsersMsgOpt.get());
    } else {
      return new RoomMessageJson();
    }
  }

  /**
   * メッセージを送信します。
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public PostMessageJson register(@PathVariable Integer roomId, @RequestBody @Validated MessageForm form,
                                  HttpServletRequest req) {

    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    if (!sesUser.getUserId().equals(form.getUserId())) {
      throw new AccessPermissonException(Msg.FORBIDDEN_USER, true);
    }

    if (!roomService.isRoomUser(sesUser.getUserId(), roomId)) {
      throw new AccessPermissonException(Msg.FORBIDDEN_ROOM, true);
    }

    SaveMessageDto dto = messageService.register(roomId, form.getUserId(), form.getContent());

    return PostMessageJson.create(dto);
  }

  @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
  public DelMessageJson register(@PathVariable Integer roomId, @PathVariable Long messageId, HttpServletRequest req) {
    SesUser sesUser = visitorManager.getSesUserOpt(Cookies.getKey(req))
      .orElseThrow(() -> new AuthException(Msg.FAIL_AUTH, true));

    if (!roomService.isRoomUser(sesUser.getUserId(), roomId)) {
      throw new AccessPermissonException(Msg.FORBIDDEN_ROOM, true);
    }
    if (!messageService.isMessageUser(sesUser.getUserId(), messageId)) {
      throw new AccessPermissonException(Msg.FORBIDDEN_MSG, true);
    }
    return DelMessageJson.create(messageService.delete(roomId, sesUser.getUserId(), messageId));
  }
}
