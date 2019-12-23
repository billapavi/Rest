package com.rotbot.billa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotbot.billa.beans.User;
import com.rotbot.billa.repositories.IUserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {

	@Autowired
	private IUserRepository userRepo;
	
	
	public List<User> getAllUserProfiles()
	{
		ArrayList<User>  userProfiles = new ArrayList<>();
		userRepo.findAll().forEach(userProfiles::add);
		return userProfiles;
//		return userRepo.findAll();
	}
	boolean firstTime = true;
	public String addUserProfile(User profile)
	{
		userRepo.save(profile);
		
		return userRepo.findByUserName(profile.getUserName()).toString();
	}
}
