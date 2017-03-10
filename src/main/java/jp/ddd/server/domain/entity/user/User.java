package jp.ddd.server.domain.entity.user;

import jp.ddd.server.adapter.web.controller.input.user.UserForm;
import jp.ddd.server.domain.entity.Entity;
import jp.ddd.server.domain.entity.user.core.HashPass;
import jp.ddd.server.domain.entity.user.core.LoginId;
import jp.ddd.server.domain.entity.user.core.UserId;
import jp.ddd.server.adapter.gateway.rds.entity.UserExt;
import jp.ddd.server.other.utils.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.springframework.stereotype.Component;

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

    public static User create(UserExt userExt) {
        val info = UserInfo.builder().email(userExt.getEmail()).name(userExt.getName()).tel(userExt.getTel()).build();
        return User.builder() //
          .id(new UserId(userExt.getId())) //
          .loginId(new LoginId(userExt.getLoginId())) //
          .hashPass(new HashPass(userExt.getPass())) //
          .status(userExt.getStatus()) //
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