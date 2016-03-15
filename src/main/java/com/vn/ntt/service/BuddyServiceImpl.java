package com.vn.ntt.service;

import com.vn.ntt.constant.SystemConstant;
import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.enums.PokeType;
import com.vn.ntt.repository.BuddyRepository;
import com.vn.ntt.repository.HashtagRepository;
import com.vn.ntt.until.MeasureUntil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vn.ntt.constant.SystemConstant.BUDDY_LIST_EMPTY;
import static com.vn.ntt.until.MeasureUntil.getDistance;

/**
 * Created by bangnl on 3/9/2016.
 */
@Service
@Transactional
public class BuddyServiceImpl  extends ModelServiceImpl<Buddy>  implements BuddyService{

    private final BuddyRepository buddyRepository;

    private final NotificationService notifiSv;

    private final HashtagRepository hashtagRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    public BuddyServiceImpl( BuddyRepository buddyRepository,
                             NotificationService notifiSv, HashtagRepository hashtagRepository){
        super(buddyRepository);
        this.buddyRepository = buddyRepository;
        this.notifiSv = notifiSv;
        this.hashtagRepository = hashtagRepository;
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

        DtoForPointAndDistance pointAndDistance = new DtoForPointAndDistance(buddy);
        return this.buddyRepository.findByLocationNear(pointAndDistance.getPoint(), pointAndDistance.getDistance());
    }

    /**
     * DTO for point and distance
     *
     * **/
    private static class DtoForPointAndDistance{
        private Point point;
        private Distance distance;
        DtoForPointAndDistance(Buddy buddy){
            Point point = new Point(buddy.getLocation()[0], buddy.getLocation()[1]);
            Distance distance = MeasureUntil.getDistanceByMet(buddy.getRadius());
        }

        private Point getPoint() {
            return point;
        }

        private Distance getDistance() {
            return distance;
        }
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
        List<String> hashes = getListHashtagOfBuddy(buddy);
        return this.buddyRepository.findByHashtagsHashIn(hashes);
    }

    private List<String> getListHashtagOfBuddy(Buddy buddy){
        List<Hashtag> hashtags = buddy.getHashtags();
        List<String> hashes = new ArrayList<>();
        hashtags.forEach(h -> hashes.add(h.getHash()));
        return hashes;
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
        buddies.stream().forEach(bd -> bd.setDistance(getDistance(buddy.getLocation(), bd.getLocation())));
        buddies.sort((bd1, bd2) -> {
            return (int) (bd1.getDistance() - bd2.getDistance());
        });
        return buddies;
    }

    @Override
    public List<Buddy> getListBuddySameHashtag(Buddy buddy) {
        if(ArrayUtils.isEmpty(buddy.getLocation())){
            return this.findByArrayHashtag(buddy);
        }

        Buddy buddyData = this.findByToken(buddy.getToken());
        DtoForPointAndDistance pointAndDistance = new DtoForPointAndDistance(buddy);
        List<String> hashes = getListHashtagOfBuddy(buddy);
        return this.buddyRepository.
                findByLocationNearAndHashtagsHashIn(pointAndDistance.getPoint(), pointAndDistance.getDistance(), hashes);
    }

    private boolean compareHashtag(String hash,Buddy bd){
        boolean check = false;
        for(Hashtag h : bd.getHashtags()){
            if(hash.matches("/.*"+h.getHash()+".*/i")){
                check = true;
                break;
            }
        }
        return check;
    }

        @Override
        public Buddy registerBuddy(Buddy buddy) {
            Buddy buddydb = this.findByToken(buddy.getToken());
            if(buddydb != null){
                buddydb.setHashtags(buddy.getHashtags());
                super.save(buddydb);
                return buddydb;
            }
            super.save(buddy);
            return buddy;
        }
}
