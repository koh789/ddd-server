package jp.ddd.server.web.form.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.ddd.server.data.BaseVisitor;
import jp.ddd.server.utils.Msg;
import jp.ddd.server.utils.Regex;
import jp.ddd.server.web.form.Form;

public class UserForm extends BaseVisitor implements Form {
    private static final long serialVersionUID = 1L;

    @NotNull(message = Msg.NOT_NULL)
    @Pattern(regexp = Regex.EMAIL_AND_TEL, message = Msg.ONLY_TEL_OR_EMAIL)
    private String loginId;

    @Size(min = 3, max = 30, message = Msg.INPUT_3TO30)
    @NotNull(message = Msg.NOT_NULL)
    private String name;

    @NotNull(message = Msg.NOT_NULL)
    @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
    @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
    private String password;

    @NotNull(message = Msg.NOT_NULL)
    @Size(min = 6, max = 30, message = Msg.INPUT_6TO30)
    @Pattern(regexp = Regex.PASSWORD, message = Msg.ONLY_EN_OR_NUM)
    private String confirmedPassword;

    @Size(min = 0, max = 200, message = Msg.INPUT_TO200)
    private String description;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
