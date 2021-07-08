package com.athensoft.prototype.mvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.athensoft.prototype.error.exceptions.UserAlreadyExistsException;
import com.athensoft.prototype.error.exceptions.UserNotFoundException;
import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public ResponseEntity<List<User>> getUserListAll() {
		return ResponseEntity.ok(repo.findAll());
	}

	public ResponseEntity<User> getUserById(int userId) {
		User user = repo.findById(userId).orElseThrow(() -> new UserNotFoundException());

		return ResponseEntity.ok(user);
	}

	public ResponseEntity<User> createUser(User user) {
		if (repo.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException();
		}
		LOGGER.debug("create user:" + user);
		return new ResponseEntity<>(repo.save(user), HttpStatus.CREATED);
	}

	public ResponseEntity<User> saveUser(User user) {
		return ResponseEntity.ok(repo.save(user));
	}

	public ResponseEntity<User> updateUser(User user) {
		if (!repo.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		LOGGER.debug("update user:" + user);
		return ResponseEntity.ok(repo.save(user));
	}

	public ResponseEntity<User> deleteUserById(int userId) {
		if (!repo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		User user = repo.findById(userId).get();
		LOGGER.debug("delete user:" + user);
		repo.deleteById(userId);
		return ResponseEntity.ok(user);
	}

	public ResponseEntity<User> deleteUser(User user) {
		if (!repo.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		LOGGER.debug("delete user:" + user);
		repo.delete(user);
		return ResponseEntity.ok(user);
	}

}
