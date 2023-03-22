package com.rishabhtech.hotelservice.controllers;

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

import com.rishabhtech.hotelservice.entity.Hotel;
import com.rishabhtech.hotelservice.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel createdHotel = this.hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(createdHotel,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotel()
	{
		return new ResponseEntity<>(this.hotelService.getAllHotel(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable(name = "id") String hotelId)
	{
		return new ResponseEntity<Hotel>(this.hotelService.get(hotelId),HttpStatus.OK);
	}
}
