package com.athensoft.prototype.error.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3231332530981116656L;

	public UserNotFoundException() {
		super("User is not found");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}