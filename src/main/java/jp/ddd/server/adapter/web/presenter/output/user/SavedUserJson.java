package jp.ddd.server.adapter.web.presenter.output.user;

import jp.ddd.server.domain.model.user.core.UserId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by noguchi_kohei 
 */
@Builder
@Data
public class SavedUserJson implements Serializable {
    private static final long serialVersionUID = 910139523300534581L;

    private Integer userId;

    public static SavedUserJson create(UserId userId) {
        return SavedUserJson.builder().userId(userId.getId()).build();
    }
}
