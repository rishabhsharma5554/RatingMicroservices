package com.rishabhtech.userservice.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhtech.userservice.entity.User;
import com.rishabhtech.userservice.exception.ResourceNotFoundException;
import com.rishabhtech.userservice.repo.UserRepo;
import com.rishabhtech.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User saveUser(User user) {
		String randomID = UUID.randomUUID().toString();
		user.setUserId(randomID);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(String userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		return user;
	}

	@Override
	public void deleteUserById(String userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		userRepo.delete(user);
	}

	@Override
	public User updateUserById(User user,String userId) {
		User userFromDB = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userid", userId));
		userFromDB.setName(user.getName());
		userFromDB.setAbout(user.getAbout());
		userFromDB.setEmail(user.getEmail());
		return userRepo.save(userFromDB);
	}

}
