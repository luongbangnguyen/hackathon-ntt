package org.spring.mongdb.service;

import org.spring.mongdb.entity.Hash;
import org.spring.mongdb.repository.HashRepository;
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
