package com.athensoft.prototype.error.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -694504633335055824L;

	public UserAlreadyExistsException() {
		super("User already exists");
	}
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
