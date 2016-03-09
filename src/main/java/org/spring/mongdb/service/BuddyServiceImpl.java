package org.spring.mongdb.service;

import org.spring.mongdb.entity.Buddy;
import org.spring.mongdb.repository.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bangnl on 3/9/2016.
 */
@Service
@Transactional
public class BuddyServiceImpl  extends ModelServiceImpl<Buddy>  implements BuddyService{
    private BuddyRepository buddyRepository;
    @Autowired
    public BuddyServiceImpl( BuddyRepository buddyRepository){
        super(buddyRepository);
        this.buddyRepository = buddyRepository;
        }
}
