package com.rishabhtech.userservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabhtech.userservice.entity.User;
import com.rishabhtech.userservice.exception.APIResponse;
import com.rishabhtech.userservice.service.UserService;
import com.rishabhtech.userservice.service.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getUsers()
	{
		List<User> allUser = this.userService.getAllUser();
		return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User savedUser = this.userService.saveUser(user);
		
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}
	
	//single user id
	private AtomicInteger retryCount = new AtomicInteger(0);

	@GetMapping("/{id}")
//	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod="ratingHotelFallbackMethod")
	@Retry(name="ratingHotelRetry", fallbackMethod="ratingHotelFallbackMethod")
	public ResponseEntity<User> getUserById(@PathVariable(name="id") String userId) {
	    logger.info("Get Single User Handler: UserController");
	    logger.info("Retry Count : {}", retryCount.getAndIncrement());
	    User userById = this.userService.getUserById(userId);
	    return new ResponseEntity<>(userById, HttpStatus.OK);
	}

	// Fallback method for circuit breaker and retry
	public ResponseEntity<User> ratingHotelFallbackMethod(String userId, Exception ex) {
	    logger.warn("Fallback is executed because the service is down: " + ex.getMessage());
	    User user = User.builder()
	            .email("dummy@gmail.com")
	            .name("Dummy")
	            .about("This user is created due to some service being down.")
	            .userId("xxxx")
	            .build();
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable(name="id") String userId)
	{
		this.userService.deleteUserById(userId);
		return new ResponseEntity<APIResponse>(new APIResponse().builder().message(userId).success(true).httpStatus(HttpStatus.OK).date(new Date()).build(),HttpStatus.OK);
	}
	
	
}
