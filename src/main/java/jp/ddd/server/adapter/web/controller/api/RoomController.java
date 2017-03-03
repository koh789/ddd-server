package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.controller.input.room.RoomForm;
import jp.ddd.server.adapter.web.presenter.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.output.room.SavedRoomJson;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.domain.repository.RoomRepository;
import jp.ddd.server.domain.repository.UserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.DsLists;
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
@RequestMapping(value = "/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController extends BaseApi {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<SavedRoomJson> register(HttpServletRequest req, @RequestBody @Validated RoomForm roomForm) {
        val loginUserId = userRepository.getOptBySid(Cookies.getKey(req)) //
          .map(u -> u.getId()).orElseThrow(() -> new AuthException());

        val joinUserIds = DsLists.toImt(roomForm.getJoinUserIds()).collect(uid -> new UserId(uid));
        val result = roomRepository.register(loginUserId, roomForm.getRoomName(), joinUserIds);

        return ResultJson.create(SavedRoomJson.create(result));
    }

    //    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT)
    //    public ResultJson<String> add(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
    //      @RequestBody @Validated UpdateRoomForm form) {
    //
    //        val loginUserId = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req)) //
    //          .map(su -> su.getUserId()).orElseThrow(() -> new AuthException());
    //
    //        val results = ExtRoom //
    //          .addRoomUser(roomId, DsLists.toImt(form.getUserIds()), roomUserRepository)
    //          .collect(ru -> SavedRoomUserJson.create(ru.getId(), ru.getUserId(), ru.getJoinDt()));
    //
    //        return ResultJson.create(results);
    //    }
}
