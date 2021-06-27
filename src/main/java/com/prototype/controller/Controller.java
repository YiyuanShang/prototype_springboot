package com.prototype.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototype.entity.Person;


@RestController
public class Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	@GetMapping("/string")
	public String getString() {
		LOGGER.debug("This is a debug message");
		LOGGER.info("This is a info message");
		LOGGER.warn("This is a warn message");
		LOGGER.error("This is a error message");
		return "returned result from getString()";
	}

	@GetMapping("/object")
	public Person getObject() {
		Person person = new Person();
		person.setName("John");
		person.setAge(20);
		return person;
	}
}
