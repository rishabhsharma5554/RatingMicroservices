package com.rishabhtech.ratingservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhtech.ratingservice.entity.Rating;
import com.rishabhtech.ratingservice.repo.RatingRepo;
import com.rishabhtech.ratingservice.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;
	
	
	@Override
	public Rating createRating(Rating rating) {
		return this.ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return this.ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return this.ratingRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return this.ratingRepo.findByHotelId(hotelId);
	}

}
