package jp.ddd.server.domain.repository.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.ddd.server.domain.repository.test.TestRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@Ignore
public class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    public void saveTest() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.execute(() -> {
            for (long i = 10000000L; i < 11000000L; i++) {
                testRepository.save(i, "aaa" + i, "テスト情報です。");
            }
        });
        pool.execute(() -> {
            for (long i = 11000000L; i < 12000000L; i++) {
                testRepository.save(i, "bbb" + i, "テスト情報です。");
            }
        });
        pool.execute(() -> {
            for (long i = 12000000L; i < 13000000L; i++) {
                testRepository.save(i, "ccc" + i, "テスト情報です。");
            }
        });
        pool.execute(() -> {
            for (long i = 13000000L; i < 14000000L; i++) {
                testRepository.save(i, "ccc" + i, "テスト情報です。");
            }
        });
        pool.execute(() -> {
            for (long i = 14000000L; i < 15000000L; i++) {
                testRepository.save(i, "ccc" + i, "テスト情報です。");
            }
        });
        try {
            Thread.sleep(100000000000000L);
        } catch (InterruptedException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

    }
}
