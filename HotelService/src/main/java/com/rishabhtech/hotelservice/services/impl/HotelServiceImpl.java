package com.rishabhtech.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabhtech.hotelservice.entity.Hotel;
import com.rishabhtech.hotelservice.exception.ResourceNotFoundException;
import com.rishabhtech.hotelservice.repo.HotelRepo;
import com.rishabhtech.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	HotelRepo hotelRepo;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return this.hotelRepo.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return this.hotelRepo.findAll();
	}

	@Override
	public Hotel get(String hotelId) {
		return this.hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel","id",hotelId));
		
	}

}
