package com.rotbot.billa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rotbot.billa.beans.User;
@Component
@Repository
public interface IUserRepository extends JpaRepository<User, String>{

	public User findByUserName(String userName);
}
