package com.vn.ntt;

import com.vn.ntt.entity.Buddy;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by bangnl on 3/9/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest{
    @Test
    public void test(){
        Buddy buddy = new Buddy();
        assertNotNull(buddy);
    }
}