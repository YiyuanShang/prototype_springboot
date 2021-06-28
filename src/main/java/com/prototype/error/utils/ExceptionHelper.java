package com.prototype.error.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prototype.error.controller.ErrorController;
import com.prototype.error.exceptions.PersonNotFoundException;

//@ControllerAdvice
//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages = "com.prototype.error.controller")
//@ControllerAdvice(assignableTypes = {ErrorController.class})
public class ExceptionHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHelper.class);
	
//	@ExceptionHandler(PersonNotFoundException.class)
//	public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException exception) {
//		LOGGER.error(exception.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
//	}

}
