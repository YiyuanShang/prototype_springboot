package com.athensoft.prototype.error.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -694504633335055824L;

	public UserAlreadyExistsException() {
		super("User already exists");
	}
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
