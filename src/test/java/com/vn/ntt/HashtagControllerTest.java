package com.vn.ntt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by bangnl on 3/12/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
public class HashtagControllerTest {

    @Test
    public void test(){
        RestTemplate template = new RestTemplate();
        Object obj = template.getForObject("http://localhost:9000/get-list-hash",Object.class);
        List<String> arrays = (List<String>)obj;

        assertNotEquals(arrays.size(), 0);
    }
}
