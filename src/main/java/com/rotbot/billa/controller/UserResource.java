//package com.rotbot.billa.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rotbot.billa.beans.User;
//import com.rotbot.billa.exceptions.UserNotFoundException;
//import com.rotbot.billa.repositories.IUserRepository;
//
//@RestController("/")
//public class UserResource {
//
//	
//	@Autowired
//	private IUserRepository uservice;
//	
//	@GetMapping("/all")
//	public List<User> findAllUsers()
//	{
//		System.out.println(uservice.findAll()+" all user services");
//		return 	uservice.findAll();
//	}
//	@GetMapping("/Users/{userName}")
//	public User findUser(@PathVariable("userName") final String userName)
//	{
//		System.out.println("all users called");
//		if(uservice.findByUserName(userName)==null)
//		{
//			throw new UserNotFoundException("usernotfound");
//		}
//		return 	uservice.findByUserName(userName);
//	}
//	
//	public UserResource() {
//		// TODO Auto-generated constructor stub
//	}
//}
