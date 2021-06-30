package com.athensoft.prototype.error.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException() {
		super();
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}

}
