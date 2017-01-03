package jp.ddd.server.domain.repository.running;

import static org.junit.Assert.assertTrue;

import jp.ddd.server.domain.repository.admin.AdministratorRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class AdministratorRepositoryTest {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    public void findByAdminIdAndPasswordAndDeletedTest() {
        System.out.println("start");
        try {

        } catch (Exception e) {
            assertTrue(false);
        }

    }

}
