package jp.ddd.server.usecase.gateway.dynamodb.running;

import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.usecase.gateway.dynamodb.SequenceDynGateway;
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
public class SequenceDynGatewayRunTest {
    @Autowired
    private SequenceDynGateway sequenceDynGateway;

    @Test
    public void incrementNumTest() {
        try {
            sequenceDynGateway.incrementNum(UserDyn.TABLE_NAME);
            System.out.println("");
        } catch (Exception e) {
            fail();
        }
    }
}
