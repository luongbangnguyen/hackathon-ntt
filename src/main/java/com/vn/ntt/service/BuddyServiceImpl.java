package com.vn.ntt.service;

import com.google.common.collect.Lists;
import com.mysema.query.BooleanBuilder;
import com.vn.ntt.constant.SystemConstant;
import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.entity.QBuddy;
import com.vn.ntt.enums.PokeType;
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

    private final NotificationService notifiSv;


    @Autowired
    public BuddyServiceImpl( BuddyRepository buddyRepository, NotificationService notifiSv){
        super(buddyRepository);
        this.buddyRepository = buddyRepository;
        this.notifiSv = notifiSv;
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
            buddy.setRadius(SystemConstant.DISTANCE_DEFAULT);
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

        if(CollectionUtils.isEmpty(buddy.getHashtags())){
            return BUDDY_LIST_EMPTY;
        }
        BooleanBuilder builder = new BooleanBuilder();
        List<Hashtag> hashtags = buddy.getHashtags();

        for(Hashtag has : hashtags){
            builder.or(QBuddy.buddy.hashtags.any().hash.contains(has.getHash()));
        }
        return Lists.newArrayList(this.buddyRepository.findAll(builder));
    }

    @Override
    public boolean updateLocation(Buddy buddy) {
        if(buddy == null){
            throw new IllegalArgumentException("buddy must not null");
        }

        if(StringUtils.isBlank(buddy.getToken())){
            throw new IllegalArgumentException("token of buddy must not empty");
        }

        Buddy buddyUpdate = this.buddyRepository.findByToken(buddy.getToken());
        buddyUpdate.setLocation(buddy.getLocation());
        buddyUpdate.setRadius(buddy.getRadius());
        this.buddyRepository.save(buddyUpdate);
        return true;

    }

    @Override
    public boolean pokeOrAccept(String tokenSend, String tokenReceive, PokeType pokeType) {
        Buddy sender = this.buddyRepository.findByToken(tokenSend);
        sender.setPokeType(pokeType);
        this.notifiSv.notificationToOneBuddy(sender, tokenReceive);
        return false;

    }

    @Override
    public Buddy findByToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new IllegalArgumentException("token must not empty");
        }
        return this.buddyRepository.findByToken(token);

    }

    @Override
    public List<Buddy> serverSendBuddy(Buddy buddy) {
        List<Buddy> buddies = this.findByLocationWithin(buddy);
        if(CollectionUtils.isEmpty(buddies)){
            return  buddies;
        }
        this.notifiSv.notificationListBuddy(buddy, buddies);
        return buddies;
    }
}
