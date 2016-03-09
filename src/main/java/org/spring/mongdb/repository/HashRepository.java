package org.spring.mongdb.repository;

import org.spring.mongdb.entity.Hash;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bangnl on 3/9/2016.
 */
public interface HashRepository extends MongoRepository<Hash,String>{
}
