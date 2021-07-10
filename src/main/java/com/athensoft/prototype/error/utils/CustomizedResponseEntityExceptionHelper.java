package com.athensoft.prototype.error.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.athensoft.prototype.error.controller.ErrorController;
import com.athensoft.prototype.error.entity.ErrorDetails;
import com.athensoft.prototype.error.exceptions.PersonNotFoundException;

//@ControllerAdvice
//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages = "com.athensoft.prototype.error.controller")
//@ControllerAdvice(assignableTypes = {ErrorController.class})
public class CustomizedResponseEntityExceptionHelper extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHelper.class);
	
//	@ExceptionHandler(PersonNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handlePersonNotFoundException(PersonNotFoundException ex,
//			WebRequest request) {
//		LOGGER.error(ex.getMessage());
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
//	}
}
