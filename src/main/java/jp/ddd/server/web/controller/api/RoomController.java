package jp.ddd.server.web.controller.api;

import jp.ddd.server.web.controller.base.BaseApi;
import jp.ddd.server.web.data.form.user.RegisterUserJson;
import jp.ddd.server.web.data.form.user.UserForm;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RegisterUserJson register(@RequestBody @Validated UserForm userForm, HttpServletRequest req) {

        //TODO
        return null;
    }
}
