package com.rishabhtech.userservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.rishabhtech.userservice.entity.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//get
	
	//post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	//put
	@PutMapping("/ratings/{ratId}")
	public Rating updateRating(@PathVariable String ratId,Rating rating);

	@DeleteMapping("/ratings/{ratId}")
	public void deleteRating(@PathVariable String ratId);
}
