package com.vn.ntt.controller;

import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bangnl on 3/11/2016.
 */

@RestController
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    @RequestMapping("get-list-hash")
    public List<Hashtag> getListHash(@RequestParam String keyword){
        return hashtagService.findByHash(keyword);
    }

}
