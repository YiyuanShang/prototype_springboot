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
import org.springframework.web.bind.annotation.ResponseBody;

import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.dao.UserRepository;
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
		return userService.getUserListAll();
	}
	
	@GetMapping("/users/{userId}")
	@ResponseBody
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	
	@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.debug("creating user:" + user);
        return userService.saveUser(user);
    }
	
	@PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
		LOGGER.debug("updating user:" + user);
        return userService.saveUser(user);
    }
	
	@DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable int userId) {
		LOGGER.debug("deleting user id:" + userId);
        return userService.deleteUserById(userId);
    }
	
	@DeleteMapping("/users")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
		LOGGER.debug("deleting user:" + user);
        return userService.deleteUser(user);
    }
	
	@PostMapping("/users/batch")
	public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
		LOGGER.debug("creating users:" + users);
        return userService.createUsers(users);
    }
	
	@PutMapping("/users/batch")
	public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> users) {
		LOGGER.debug("updating users:" + users);
        return userService.updateUsers(users);
    }
	
	@DeleteMapping("/users/batch")
	public ResponseEntity<List<User>> deleteUsers(@RequestBody List<User> users) {
		LOGGER.debug("deleting users:" + users);
        return userService.deleteUsers(users);
    }

}
