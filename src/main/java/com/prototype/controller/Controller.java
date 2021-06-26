package com.prototype.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototype.entity.Person;

@RestController
public class Controller {
	@GetMapping("/string")
	public String getString() {
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
