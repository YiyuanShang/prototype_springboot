package com.athensoft.prototype.error.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.athensoft.prototype.error.exceptions.PersonNotFoundException;
import com.athensoft.prototype.error.utils.ExceptionHelper;

@RestController
public class ErrorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	
	@GetMapping("/error1/notfound")
	public Object getErrorNotFound() {
		LOGGER.debug("entering /error1/notfound");
		throw new PersonNotFoundException("Person Not Found Message");
	}

	
	
}
