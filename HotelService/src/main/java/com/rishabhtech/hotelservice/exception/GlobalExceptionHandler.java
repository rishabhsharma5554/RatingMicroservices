package com.rishabhtech.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		APIResponse resp = APIResponse.builder().message(message).success(false).httpStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
	}
}
