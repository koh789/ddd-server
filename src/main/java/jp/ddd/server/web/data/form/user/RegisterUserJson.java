package jp.ddd.server.web.data.form.user;

import jp.ddd.server.web.data.Json;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserJson implements Json {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    public static RegisterUserJson create(Integer userId) {
        return new RegisterUserJson(userId);
    }
}
