package com.vn.ntt.service;

import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;
import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.QBuddy;
import com.vn.ntt.repository.BuddyRepository;
import com.vn.ntt.until.MeasureUntil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vn.ntt.constant.SystemConstant.BUDDY_LIST_EMPTY;

/**
 * Created by bangnl on 3/9/2016.
 */
@Service
@Transactional
public class BuddyServiceImpl  extends ModelServiceImpl<Buddy>  implements BuddyService{

    private final BuddyRepository buddyRepository;

    @Autowired
    public BuddyServiceImpl( BuddyRepository buddyRepository){
        super(buddyRepository);
        this.buddyRepository = buddyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Buddy> findByLocationWithin(Buddy buddy) {
        if(buddy == null){
            throw new IllegalArgumentException("buddy must not empty");
        }
        if(ArrayUtils.isEmpty(buddy.getLocation())){
            throw new IllegalArgumentException("lat or lon must not empty");
        }

        if(buddy.getRadius() == null){
            throw  new IllegalArgumentException("radius must not empty");
        }

        Point point = new Point(buddy.getLocation()[0], buddy.getLocation()[1]);
        Distance distance = MeasureUntil.getDistanceByMet(buddy.getRadius());
        return this.buddyRepository.findByLocationNear(point, distance);
    }

    @Override
    public Buddy findAndRegisterBuddy(Buddy buddy) {

        if(StringUtils.isEmpty(buddy.getToken())) {
            throw new IllegalArgumentException("token of buddy can not empty");
        }

        Buddy buddyResult = this.buddyRepository.findByToken(buddy.getToken());

        if(buddyResult == null){
           buddyResult = this.buddyRepository.save(buddy);
        }
        return buddyResult;
    }

    @Override
    public List<Buddy> findByArrayHashtag(Buddy buddy) {
        if(buddy == null){
            throw new IllegalArgumentException("buddy must not null");
        }

        if(CollectionUtils.isEmpty(buddy.getHashtag())){
            return BUDDY_LIST_EMPTY;
        }
        BooleanBuilder builder = new BooleanBuilder();
        List<String> hashtags = buddy.getHashtag();

        for(String str : hashtags){
            builder.or(QBuddy.buddy.hashtag.contains(str));
        }
        return Lists.newArrayList(this.buddyRepository.findAll(builder));
    }
}
