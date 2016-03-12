package com.vn.ntt.service;

import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.repository.HashtagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vn.ntt.until.PageUntil.getPageDefault;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by bangnl on 3/11/16.
 */

@Service
public class HashtagServiceImpl implements HashtagService{

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Hashtag> findByHash(String hash) {

        if(StringUtils.isBlank(hash)) {
            return this.hashtagRepository.findAll(getPageDefault("hash")).getContent();
        }
        return this.hashtagRepository.findByHashContaining(hash, getPageDefault("hash"));
    }

}
