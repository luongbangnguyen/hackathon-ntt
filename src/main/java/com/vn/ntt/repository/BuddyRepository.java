package com.vn.ntt.repository;

import com.vn.ntt.entity.Buddy;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by bangnl on 3/9/2016.
 */
public interface BuddyRepository extends MongoRepository<Buddy,String>{
    List<Buddy> findByLocationNear(Point point, Distance distance);
    Buddy findByToken(String token);
}
