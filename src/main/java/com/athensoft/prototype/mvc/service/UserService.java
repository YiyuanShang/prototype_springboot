package com.athensoft.prototype.mvc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.error.exceptions.UserAlreadyExistsException;
import com.athensoft.prototype.error.exceptions.UserNotFoundException;
import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;

@Service
public class UserService {
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public List<User> getUserListAll() {
		return repo.findAll();
	}

	public User getUserById(int userId) {
		if (!repo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		User user = repo.findById(userId).get();
		return user;
	}

	public User createUser(User user) {
		if (repo.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException();
		}
		return repo.save(user);
	}

	public User saveUser(User user) {
		return repo.save(user);
	}

	public User updateUser(User user) {
		if (!repo.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		return repo.save(user);
	}

	public User deleteUserById(int userId) {
		if (!repo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		User user = repo.findById(userId).get();
		repo.deleteById(userId);
		return user;
	}

	public User deleteUser(User user) {
		if (!repo.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		repo.delete(user);
		return user;
	}

}
