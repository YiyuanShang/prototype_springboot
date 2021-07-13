package com.athensoft.prototype.error.exceptions;

import com.athensoft.prototype.mvc.entity.User;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3231332530981116656L;

	public UserNotFoundException() {
		super("User is not found");
	}
	
	public UserNotFoundException(User user) {
		super("User " +  user.getName() + " of " + user.getAge() + " years old, Id " + user.getUserId() + ", is not found!");
	}
	
	public UserNotFoundException(int userId) {
		super("User Id " + userId + " is not found!");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
