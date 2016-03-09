package org.spring.mongdb.repository;

import org.spring.mongdb.entity.Buddy;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bangnl on 3/9/2016.
 */
public interface BuddyRepository extends MongoRepository<Buddy,String>{
}
