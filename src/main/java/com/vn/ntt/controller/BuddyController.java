package com.vn.ntt.controller;

import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.enums.PokeType;
import com.vn.ntt.service.BuddyService;
import com.vn.ntt.service.HashtagService;
import com.vn.ntt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuddyController {

	@Autowired
	private BuddyService buddySv;

	@Autowired
	private HashtagService hashtagService;

	/**
	 * add new buddy
	 * @param buddy
	 * @return
	 */
	@RequestMapping(path = "register",method = RequestMethod.POST)
	public boolean addBuddy(@RequestBody Buddy buddy){
		this.buddySv.save(buddy);
		return true;
	}

	@RequestMapping(path = "update-location",method = RequestMethod.PUT)
	public boolean updateLocation(@RequestBody Buddy buddy){
		return this.buddySv.updateLocation(buddy);
	}

	@RequestMapping(path = "poke-accept", method = RequestMethod.POST)
	public boolean pokeOrAccept(@RequestBody String tokenSend,
								@RequestParam String tokenReceive,
								@RequestParam PokeType pokeType){
		return this.buddySv.pokeOrAccept(tokenSend, tokenReceive, pokeType);
	}

	@RequestMapping(path = "get-user")
	public Buddy getUser(@RequestParam String token){
		return this.buddySv.findByToken(token);
	}


	@RequestMapping(path = "get-list-buddy", method = RequestMethod.POST)
	public List<Buddy> getListBuddy(@RequestBody Buddy buddy){
		return this.buddySv.findByLocationWithin(buddy);
	}


    @RequestMapping(path = "server-send-buddy", method = RequestMethod.POST)
    public List<Buddy> serverSendBuddy(Buddy buddy){
        return this.serverSendBuddy(buddy);
    }

}
