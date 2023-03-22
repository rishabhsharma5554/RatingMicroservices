package com.rishabhtech.hotelservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishabhtech.hotelservice.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
