package jp.ddd.server.usecase.gateway.dynamodb.running;

import jp.ddd.server.adapter.gateway.dynamodb.table.MessageDyn;
import jp.ddd.server.domain.repository.MessageRepository;
import jp.ddd.server.other.data.common.IdPage;
import jp.ddd.server.usecase.gateway.dynamodb.MessageDynGateway;
import jp.ddd.server.usecase.gateway.dynamodb.RoomDynGateway;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class MessageDynGatewayRunTest {
    @Autowired
    private MessageDynGateway messageDynGateway;

    @Test
    public void findDescTest() {
        try {

            val results = messageDynGateway.findDesc(1, IdPage.init(10));
            System.out.println(results);
        } catch (Exception e) {
            fail();
        }
    }
}
