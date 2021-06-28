package com.prototype.error.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prototype.error.exceptions.PersonNotFoundException;

@RestController
public class ErrorController {
	@GetMapping("/error1/notfound")
	public ResponseEntity<String> getErrorNotFound() {
		throw new PersonNotFoundException("Person Not Found Message");
	}

	
	
}
