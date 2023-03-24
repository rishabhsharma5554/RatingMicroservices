package com.rishabhtech.userservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rishabhtech.userservice.entity.Hotel;
import com.rishabhtech.userservice.entity.Rating;
import com.rishabhtech.userservice.entity.User;
import com.rishabhtech.userservice.exception.ResourceNotFoundException;
import com.rishabhtech.userservice.repo.UserRepo;
import com.rishabhtech.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	private HttpStatusCode StringstatusCode;
	
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
		//get user from db 
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		//get rating of users from rating service
		//http://localhost:8082/ratings/users/8ae64132-dbbb-432e-8cf0-33c61f93fd1b
		Rating[] ratings = this.restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
		
		List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();
		logger.info("{}",ratingsOfUser);
		ratingsOfUser.stream().map(rating -> {
			//http://localhost:8082/ratings/users/8ae64132-dbbb-432e-8cf0-33c61f93fd1b
			ResponseEntity<Hotel> hotelResponseEntity = this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelResponseEntity.getBody();
			HttpStatusCode statusCode = hotelResponseEntity.getStatusCode();
			logger.info("{}",hotel);
			logger.info("{}",statusCode);
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingsOfUser);
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
