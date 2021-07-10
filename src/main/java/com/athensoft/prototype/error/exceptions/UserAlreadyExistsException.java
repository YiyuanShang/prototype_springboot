package com.athensoft.prototype.error.exceptions;

import com.athensoft.prototype.mvc.entity.User;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -694504633335055824L;

	public UserAlreadyExistsException() {
		super("User already exists!");
	}

	public UserAlreadyExistsException(User user) {
		super("User " + user.getUserId() + " " + user.getName() + " of " + user.getAge() + " years old already exists!");
	}
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
