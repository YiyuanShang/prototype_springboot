package com.athensoft.prototype.mvc.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.service.UserService;


@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUserListAll() {
		LOGGER.debug("entering /users");
		return userService.getUserListAll();
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		LOGGER.debug("entering /users/" + userId);
		return userService.getUserById(userId);
	}
	
	@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.debug("entering /users");
        return userService.createUser(user);
    }
	
	@PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
		LOGGER.debug("entering /users");
        return userService.updateUser(user);
    }
	
	@DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable int userId) {
		LOGGER.debug("entering /users/" + userId);
        return userService.deleteUserById(userId);
    }
	
	@DeleteMapping("/users")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
		LOGGER.debug("entering /users");
        return userService.deleteUser(user);
    }
	

}
