package com.athensoft.prototype.error.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.athensoft.prototype.mvc.entity.User;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3231332530981116656L;

	public UserNotFoundException(int userId) {
		super("User " + userId + " is not found");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
