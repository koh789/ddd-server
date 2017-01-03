package jp.ddd.server.domain.repository.running;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.ddd.server.domain.repository.message.MessageReadRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class MessageReadRepositoryTest {

    @Autowired
    private MessageReadRepository messageReadRepository;

    @Ignore
    @Test
    public void savebyUnique() {
        for (Integer i = 6; i <= 15; i++) {
            messageReadRepository.saveByUnique(i.longValue(), 1, true);
        }
    }
}
