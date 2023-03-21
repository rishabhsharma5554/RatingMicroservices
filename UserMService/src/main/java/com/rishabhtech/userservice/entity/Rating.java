package com.rishabhtech.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	private String ratingId;
	private String userId;
	private String hotelId;
	private Integer rating;
	private String feedback;
}
