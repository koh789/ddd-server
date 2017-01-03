package jp.ddd.server.manager;

import java.util.Optional;

import jp.ddd.server.data.VisitParam;
import jp.ddd.server.domain.entity.SesAdmin;
import jp.ddd.server.domain.repository.admin.SesAdminRepository;
import jp.ddd.server.domain.repository.user.SesUserRepository;
import jp.ddd.server.utils.EntityFuncs;
import jp.ddd.server.utils.Requests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.ddd.server.domain.entity.SesUser;

/**
 * セッション情報としてのvisitor情報に関するコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Component
public class VisitorManager {
    private static final Logger log = LoggerFactory.getLogger(Requests.class);

    @Autowired
    private SesUserRepository sesUserRepository;
    @Autowired
    private SesAdminRepository sesAdminRepository;

    public void loginUser(String sid, SesUser sesUser) {
        sesUserRepository.save(sid, sesUser);
    }

    public void loginAdmin(String sid, SesAdmin sesAdmin) {
        sesAdminRepository.save(sid, sesAdmin);
    }

    /**
     * ユーザのvisitor情報のみキャッシュします。
     */
    public void store(VisitParam param) {
        sesUserRepository.getOpt(param.getSessionId()).map(savedEntity -> {
            SesUser entity = EntityFuncs.VISITOR_AND_SES_USER_TO_SES_USER.apply(param, savedEntity);
            sesUserRepository.save(entity.getSessionId(), entity);
            return entity;
        }).orElseGet(() -> {
            SesUser entity = EntityFuncs.VISITOR_TO_SES_USER.apply(param);
            sesUserRepository.save(param.getSessionId(), EntityFuncs.VISITOR_TO_SES_USER.apply(param));
            return entity;
        });
    }

    public Optional<SesUser> getSesUserOpt(String sid) {
        return sesUserRepository.getOpt(sid);
    }

    public Optional<SesAdmin> getSesAdminOpt(String sid) {
        return sesAdminRepository.getOpt(sid);
    }

    public boolean isLoginUser(String sid) {
        Optional<Boolean> isLoginOpt = getSesUserOpt(sid).map(sesUser -> sesUser.isLogin());
        if (isLoginOpt.isPresent()) {
            return isLoginOpt.get();
        } else {
            return false;
        }
    }

    public void logoutUser(String sid) {
        sesUserRepository.del(sid);
    }
}
