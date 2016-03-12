package com.vn.ntt.repository;

import com.vn.ntt.entity.Hashtag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by bangnl on 3/11/16.
 */
public interface HashtagRepository extends MongoRepository<Hashtag,String> {
    Hashtag findByHash(String hash);

    List<Hashtag> findByHashContaining(String keyword, Pageable pageable);
}
