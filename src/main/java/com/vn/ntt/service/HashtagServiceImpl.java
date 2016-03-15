package com.vn.ntt.service;

import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.repository.HashtagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.vn.ntt.until.PageUntil.getPageDefault;

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
    public List<String> findByHash(String hash) {

        if(StringUtils.isBlank(hash)) {
            return converListHashtagToListString(this.hashtagRepository.findAll());
        }
        return converListHashtagToListString(this.hashtagRepository.findByHashContaining(hash, getPageDefault("hash")));
    }

    private List<String> converListHashtagToListString(List<Hashtag> hashtags){
        List<String> strs = new ArrayList<>();
        hashtags.forEach(hashtag -> strs.add(hashtag.getHash()));
        return strs;
    }

}
