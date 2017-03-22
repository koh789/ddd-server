package jp.ddd.server.usecase.gateway.dynamodb.running;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;
import jp.ddd.server.adapter.gateway.dynamodb.table.UserDyn;
import jp.ddd.server.usecase.gateway.dynamodb.UserDynGateway;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by noguchi_kohei 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class UserDynGatewayRunTest {
    @Autowired
    private UserDynGateway userDynGateway;

    @Test
    public void saveTest() {
        try {
            //            val userDyn = UserDyn.builder().loginId("dummy@gmail.com").email("dummy@gmail.com")
            //              .pass("B5A2C96250612366EA272FFAC6D9744AAF4B45AACD96AA7CFCB931EE3B558259").tel("08010001000").build();
            //            val result = userDynGateway.save(userDyn);

//            val result = userDynGateway.findOne("aa");
            val result = userDynGateway.findByLoginId("dummy@gmail.com");
            System.out.println(result);
        } catch (Exception e) {
            fail();
        }
    }
}
