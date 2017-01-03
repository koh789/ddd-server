package jp.ddd.server.service.running;

import static org.junit.Assert.assertTrue;

import jp.ddd.server.utils.enums.AdminRole;
import jp.ddd.server.exception.AccessPermissonException;
import jp.ddd.server.service.AdminUserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class AdminUserServiceTest {
    @Autowired
    private AdminUserService adminUserService;

    @Ignore
    @Test
    public void registerTest() {
        try {
            adminUserService.register("noguchi_kohei", "noguchi_kohei", "kkk", AdminRole.SYSTEM_ADMIN,
                    "noguchi_kohei@cyberagent.co.jp", null);
            assertTrue(false);
        } catch (AccessPermissonException e) {
            assertTrue(true);
        }
    }

}
