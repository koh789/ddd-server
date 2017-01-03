package jp.ddd.server.domain.repository.running;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.ddd.server.domain.entity.User;
import jp.ddd.server.domain.repository.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getListTest() {
        try {
            Optional<Map<Integer, User>> userMapOpt = userRepository.findMapOpt(Arrays.asList(1));
            if (userMapOpt.isPresent()) {
                User user = userMapOpt.get().get(1);
                System.out.println(user.getName());
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            assertTrue(false);
        }
    }
}
