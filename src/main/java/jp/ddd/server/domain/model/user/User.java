package jp.ddd.server.domain.model.user;

import jp.ddd.server.adapter.web.controller.input.user.UserForm;
import jp.ddd.server.domain.model.Entity;
import jp.ddd.server.domain.model.user.core.HashPass;
import jp.ddd.server.domain.model.user.core.LoginId;
import jp.ddd.server.domain.model.user.core.UserId;
import jp.ddd.server.external.mysql.entity.ExtUser;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;

@Builder
@AllArgsConstructor
@Value
public class User implements Entity<User> {
    private static final long serialVersionUID = 5130723129760236659L;

    private final UserId id;

    private final LoginId loginId;

    private final HashPass hashPass;

    private final Status status;

    private final UserInfo userInfo;

    public static User create(ExtUser extUser) {
        val info = UserInfo.builder().email(extUser.getEmail()).name(extUser.getName()).tel(extUser.getTel()).build();
        return User.builder() //
          .id(new UserId(extUser.getId())) //
          .loginId(new LoginId(extUser.getLoginId())) //
          .hashPass(new HashPass(extUser.getPass())) //
          .status(extUser.getStatus()) //
          .userInfo(info) //
          .build();
    }

    public static User create(UserForm form) {
        val info = UserInfo.builder().email(form.getEmail()).name(form.getName()).tel(form.getTel()).build();
        return User.builder() //
          .loginId(new LoginId(form.getLoginId())) //
          .hashPass(HashPass.createByRawPass(form.getPassword())) //
          .status(Status.VALID) //
          .userInfo(info) //
          .build();
    }

}