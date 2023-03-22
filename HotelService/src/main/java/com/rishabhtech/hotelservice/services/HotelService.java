package com.rishabhtech.hotelservice.services;

import java.util.List;

import com.rishabhtech.hotelservice.entity.Hotel;

public interface HotelService {
	Hotel createHotel(Hotel hotel);
	List<Hotel> getAllHotel();
	Hotel get(String hotelId);

}
