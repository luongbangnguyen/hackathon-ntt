package com.vn.ntt;

import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.service.BuddyService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangnl on 3/12/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest("server.port:0")
public class BuddyServiceTest {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    BuddyService buddyService;

    public void testGetHashTagSame(){
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(new Hashtag("tired",""));
        hashtags.add(new Hashtag("home",""));
        hashtags.add(new Hashtag("usa",""));
        Buddy buddy = new Buddy();
        buddy.setToken("faZmgfLkzAQ:APA91bGE29w-8R4mTza1V84sALotSoHK0Q4jaORzQizOVO0EvrDs5SBXK0AgKgcDPvsc3NvUsbSl3xptSJ2IAL8Ln2zXsnvIyovk0Do00BkVmjnffmdznGk1jv4qJbxMx-ThGO_1n1fq");
        buddy.setName("NinHN");
        buddy.setHashtags(hashtags);
        List<Buddy> buddies = buddyService.findByArrayHashtag(buddy);
        Assert.assertNotEquals(0,buddies.size());
    }

}
