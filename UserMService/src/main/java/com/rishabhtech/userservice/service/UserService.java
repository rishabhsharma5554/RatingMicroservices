package com.rishabhtech.userservice.service;

import java.util.List;
import java.util.Set;

import com.rishabhtech.userservice.entity.User;

public interface UserService {
	public User saveUser(User user);
	List<User> getAllUser();
	User getUserById(String userId);
	void deleteUserById(String userId);
	User updateUserById(User user,String userId);
}
