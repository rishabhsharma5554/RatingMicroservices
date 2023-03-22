package com.rishabhtech.hotelservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String resourceField;
	String fieldValue;

	public ResourceNotFoundException(String resourceName, String resourceField, String fieldValue) {
		super(String.format("%s not found with this %s : %s", resourceName, resourceField, fieldValue));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.fieldValue = fieldValue;
	}

}