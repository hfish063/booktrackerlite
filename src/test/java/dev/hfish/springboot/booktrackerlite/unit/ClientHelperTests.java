package dev.hfish.springboot.booktrackerlite.unit;

import dev.hfish.springboot.booktrackerlite.restclient.ClientHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientHelperTests {
    @Autowired
    private ClientHelper clientHelper;

    @Test
    public void testFindBookKey() throws Exception {
        String expectedKey = "/works/OL19396663W";

        assertEquals(expectedKey, clientHelper.findBookKey("Beautiful Code"), "keys should match");

        assertNull(clientHelper.findBookKey(" "), "should not process empty title");
        assertNotNull(clientHelper.findBookKey("not-a-valid-book"));
    }

    @Test
    public void testRemoveHttpStatus() {
        String httpStatus = "Status <200, OK> {";

        assertEquals("                 {", clientHelper.removeHttpStatus(httpStatus));
    }
}
