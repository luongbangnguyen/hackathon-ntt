package com.vn.ntt.controller;

import com.vn.ntt.entity.Buddy;
import com.vn.ntt.service.BuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuddyController {
	
	@Autowired
	private BuddyService buddySv;
	
	/**
	 * add new buddy
	 * @param buddy
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Buddy addBuddy(@RequestBody Buddy buddy){
		return this.buddySv.save(buddy);
	}

    @RequestMapping(method = RequestMethod.PUT)
    public Buddy updateBuddy(@RequestBody Buddy buddy){
        return this.buddySv.save(buddy);
    }
}
