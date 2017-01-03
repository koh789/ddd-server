package jp.ddd.server.utils.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jp.ddd.server.exception.InternalException;

/**
 * {@link LoginIdType}のテストクラスです。
 * @author noguchi_kohei
 *
 */
public class LoginIdTypeTest {

    @Test
    public void getTest() {
        assertEquals(LoginIdType.EMAIL, LoginIdType.getBy("test@test.com"));
        try {
            LoginIdType.getBy("testtest.com");
            assertTrue(false);
        } catch (InternalException e) {
            assertTrue(true);
        }
        assertEquals(LoginIdType.TEL, LoginIdType.getBy("08045671445"));
        assertEquals(LoginIdType.TEL, LoginIdType.getBy("080-4567-1445"));
        try {
            LoginIdType.getBy("0804599671445");
            assertTrue(false);
        } catch (InternalException e) {
            assertTrue(true);
        }
    }
}
