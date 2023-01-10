package com.getjob.app.exception;

public class ResourceNotFoundException extends Throwable{

	public ResourceNotFoundException() {
		super("Resourse not found on server!!");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
