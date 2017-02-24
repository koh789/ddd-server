package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.repository.mysql.entity.Room;
import jp.ddd.server.adapter.repository.redis.entity.SessionUser;
import jp.ddd.server.adapter.repository.mysql.RoomRepository;
import jp.ddd.server.adapter.repository.mysql.RoomUserRepository;
import jp.ddd.server.adapter.repository.redis.SessionUserRepository;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.DsLists;
import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.data.input.room.RoomForm;
import jp.ddd.server.adapter.web.data.output.ResultJson;
import jp.ddd.server.adapter.web.data.output.room.SavedRoomJson;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private RoomUserRepository roomUserRepository;
    @Autowired
    private SessionUserRepository sessionUserRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<SavedRoomJson> register(HttpServletRequest req, @RequestBody @Validated RoomForm roomForm) {
        val loginUserId = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req)) //
          .map(su -> su.getUserId()).orElseThrow(() -> new AuthException());
        val joinUserIds = DsLists.toImt(roomForm.getJoinUserIds());

        val result = Room
          .registerWithRoomUser(roomRepository, roomUserRepository, loginUserId, roomForm.getRoomName(), joinUserIds);
        return ResultJson.create(SavedRoomJson.create(result));
    }

//    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT)
//    public ResultJson<String> add(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
//      @RequestBody @Validated UpdateRoomForm form) {
//
//        val loginUserId = SessionUser.getOpt(sessionUserRepository, Cookies.getKey(req)) //
//          .map(su -> su.getUserId()).orElseThrow(() -> new AuthException());
//
//        val results = Room //
//          .addRoomUser(roomId, DsLists.toImt(form.getUserIds()), roomUserRepository)
//          .collect(ru -> SavedRoomUserJson.create(ru.getId(), ru.getUserId(), ru.getJoinDt()));
//
//        return ResultJson.create(results);
//    }
}
