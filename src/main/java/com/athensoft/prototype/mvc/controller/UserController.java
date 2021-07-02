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

import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.service.UserService;



@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<User> getUserListAll() {
		return userService.getUserListAll();
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	@PostMapping("/users")
    public User createUser(@RequestBody User user) {
		LOGGER.info("create user:" + user);
        return userService.saveUser(user);
    }
	
	@PutMapping("/users")
    public User updateUser(@RequestBody User user) {
		LOGGER.info("update user:" + user);
        return userService.saveUser(user);
    }
	
	@DeleteMapping("/users/{userId}")
    public User deleteUserById(@PathVariable int userId) {
		LOGGER.info("delete user by user id " + userId);
        return userService.deleteUserById(userId);
    }
	
	@DeleteMapping("/users")
    public User deleteUser(@RequestBody User user) {
		LOGGER.info("delete user:" + user);
        return userService.deleteUser(user);
    }
	

}
