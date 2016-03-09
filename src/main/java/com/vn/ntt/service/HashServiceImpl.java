package com.vn.ntt.service;

import com.vn.ntt.entity.Hash;
import com.vn.ntt.repository.HashRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bangnl on 3/9/2016.
 */
public class HashServiceImpl extends ModelServiceImpl<Hash> implements HashService{

    private HashRepository hashRepository;

    @Autowired
    public HashServiceImpl(HashRepository hashRepository){
        super(hashRepository);
        this.hashRepository = hashRepository;
    }
}
