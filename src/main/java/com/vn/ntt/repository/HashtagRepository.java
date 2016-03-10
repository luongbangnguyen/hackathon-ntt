package com.vn.ntt.repository;

import com.vn.ntt.entity.Hashtag;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bangnl on 3/11/16.
 */
public interface HashtagRepository extends MongoRepository<Hashtag,String> {

}
