package com.athensoft.prototype.error.utils;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.athensoft.prototype.error.controller.ErrorController;
import com.athensoft.prototype.error.exceptions.PersonNotFoundException;

//@ControllerAdvice
//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages = "com.athensoft.prototype.error.controller")
//@ControllerAdvice(assignableTypes = {ErrorController.class})
public class ExceptionHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<String> handlePersonNotFoundException(HttpServletRequest request, PersonNotFoundException exception) {
		LOGGER.error(exception.getMessage());
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		LOGGER.debug("response entity:" + response.toString());
		
		return response;
	}

}
