package com.rotbot.billa.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rotbot.billa.beans.User;
@Component
public class UserDaoService {

	public static List<User> users = new ArrayList<>();
	public static int userCount =5;
	
	static {
		users.add(new User("RP5337","PRAVEEN","API","BILLA"));
		users.add(new User("1234","PRAVEEN","API","BILLA"));
		users.add(new User("7897","PRAVEEN","API","BILLA"));
		users.add(new User("R255","PRAVEEN","API","BILLA"));
	}
	
	public static List<User> findAll()
	{
		return users;
	}
	
	public static void addUser(User user)
	{
		users.add(users.get(1));
	}
	
	public static User findUser(String id )
	{
		
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if(user.getUserID().equals(id))
			{
				return user;
			}
		}
		
		return null;
	}
}
