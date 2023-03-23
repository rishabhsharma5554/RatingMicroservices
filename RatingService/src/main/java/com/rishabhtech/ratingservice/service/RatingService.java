package com.rishabhtech.ratingservice.service;

import java.util.List;

import com.rishabhtech.ratingservice.entity.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	List<Rating> getAllRating();
	List<Rating> getRatingByUserId(String userId);
	List<Rating> getRatingByHotelId(String hotelId);
}
