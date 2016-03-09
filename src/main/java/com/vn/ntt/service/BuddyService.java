package com.vn.ntt.service;

import com.vn.ntt.entity.Buddy;

import java.util.List;

/**
 * Created by bangnl on 3/9/2016.
 */
public interface BuddyService extends ModelService<Buddy>{

    /**
     * get list buddys near a user
     *
     * @param buddy
     * @return list buddy
     */
    List<Buddy> findByLocationWithin(Buddy buddy);

    /**
     * return a buddy if exist in database vice versa save and return a buddy
     *
     * @param buddy
     * @return buddy registered
     */
    Buddy findAndRegisterBuddy(Buddy buddy);


}
