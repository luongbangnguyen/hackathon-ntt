package com.vn.ntt.service;

import com.vn.ntt.entity.Hashtag;

import java.util.List;

/**
 * Created by bangnl on 3/11/16.
 */
public interface HashtagService {

    /**
     * get 10 hashtag by user send to
     *
     * @param hash
     * @return
     */
    List<Hashtag> findByHash(String hash);
}
