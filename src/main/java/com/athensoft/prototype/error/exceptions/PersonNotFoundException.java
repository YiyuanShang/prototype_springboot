package com.athensoft.prototype.error.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.athensoft.prototype.entity.Person;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5298283956036805648L;

	public PersonNotFoundException() {
		super("Person Not Found Message");
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}
	
	public PersonNotFoundException(Person person) {
		super("Person " + person.getName() + " of " + person.getAge() + " years old not found message");
	}

}
