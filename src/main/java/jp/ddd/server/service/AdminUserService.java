package jp.ddd.server.service;

import jp.ddd.server.utils.enums.AdminRole;

public interface AdminUserService {

  void register(String adminId, String password, String userName, AdminRole adminRole, String email, String note);

  void login(String sid, String adminId, String password);
}
