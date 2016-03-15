package com.vn.ntt;

import com.vn.ntt.entity.Buddy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

/**
 * Created by bangnl on 3/9/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest("server.port:0")
public class ApplicationTest{
    @Test
    public void test(){
        Buddy buddy = new Buddy();
        assertNotNull(buddy);
    }
}