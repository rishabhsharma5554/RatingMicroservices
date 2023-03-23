package com.rishabhtech.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabhtech.ratingservice.entity.Rating;
import com.rishabhtech.ratingservice.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	
	@Autowired
	private RatingService ratingService;
	
	
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRating()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRating());
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable(name = "userId") String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getRatingByUserId(id));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable(name="hotelId") String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getRatingByHotelId(hotelId));
	}
	
	
}
