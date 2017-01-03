package jp.ddd.server.service.impl;

import jp.ddd.server.domain.entity.Administrator;
import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.exception.AuthException;
import jp.ddd.server.utils.EntityFuncs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ddd.server.domain.repository.admin.AdministratorRepository;
import jp.ddd.server.utils.enums.Deleted;
import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.manager.VisitorManager;
import jp.ddd.server.service.AdminUserService;
import jp.ddd.server.utils.Const;
import jp.ddd.server.utils.Strings;

/**
 * 管理ユーザー関連のコンポーネントを管理します。
 *
 * @author noguchi_kohei
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AdministratorRepository administratorRepository;
  @Autowired
  private VisitorManager visitorManager;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void register(String adminId, String prePassword, String userName, AdminRole adminRole, String email,
                       String note) {
    if (administratorRepository.findByAdminId(adminId).isPresent()) {
      throw new AccessPermissonException("adminIdが既に存在します adminId:" + adminId, "使用済みadminIdです。");
    }
    Administrator entity = administratorRepository
      .save(Administrator.create(adminId, userName, email, Const.UNDEFINED, note, adminRole));
    String hashedPass = Strings.hashPass(prePassword, String.valueOf(entity.getId()));

    entity.setPassword(hashedPass);
    administratorRepository.save(entity);
  }

  @Override
  public void login(String sid, String adminId, String pass) {
    Administrator entity = administratorRepository.findByAdminIdAndDeleted(adminId, (Byte) Deleted.PUBLIC.getCode())
      .orElseThrow(() -> new IllegalArgumentException("can't findStm user. adminId" + adminId));

    String inputHashPass = Strings.hashPass(pass, String.valueOf(entity.getId()));
    if (!inputHashPass.equals(entity.getPassword())) {
      throw new AuthException("not accord with passoword. adminId: " + adminId);
    }
    visitorManager.loginAdmin(sid, EntityFuncs.ADMIN_TO_SES_ADMIN.apply(entity));
  }
}
