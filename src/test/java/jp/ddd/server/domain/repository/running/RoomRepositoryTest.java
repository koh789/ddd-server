package jp.ddd.server.domain.repository.running;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.ddd.server.domain.entity.Room;
import jp.ddd.server.utils.Dates;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.ddd.server.domain.repository.room.RoomRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Ignore
    @Test
    public void getStmTest() {
        try {
            Stream<Room> roomStm = roomRepository.findStm(1);
            for (Room entity : roomStm.collect(Collectors.toList())) {
                System.out.println(entity.getId());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            assertTrue(false);
        }
    }

    @Ignore
    @Test
    public void saveTest() {
        Date date = Dates.now();
        Room entity = new Room(0, 0, date, 1L, "テストルーム", 1);
        Room result = roomRepository.save(entity);

        assertNotEquals(0, result.getId());
    }

}
