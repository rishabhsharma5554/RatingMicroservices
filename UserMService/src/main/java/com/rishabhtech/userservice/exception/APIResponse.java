package com.rishabhtech.userservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
