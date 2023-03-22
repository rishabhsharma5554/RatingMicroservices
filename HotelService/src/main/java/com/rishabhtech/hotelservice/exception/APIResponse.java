package com.rishabhtech.hotelservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse {
	private String message;
	private boolean success;
	private HttpStatus httpStatus;
	private Date date;
}
