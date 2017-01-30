package jp.ddd.server.web.data.form.auth;

import jp.ddd.server.web.data.Form;
import lombok.Data;

/**
 * Created by noguchi_kohei 
 */
@Data
public class AuthForm implements Form{
    private static final long serialVersionUID = 7237378179106647211L;

    private final String loginId;

    private final String password;
}
