package com.athensoft.prototype.error.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.athensoft.prototype.error.exceptions.PersonNotFoundException;

@Controller
public class ErrorController2 {
	@GetMapping("/error2/notfound")
	public ResponseEntity<String> getErrorNotFound() {
		throw new PersonNotFoundException("Person Not Found Message");
	}
}
