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

	public ResponseEntity<List<User>> getUserListAll() {
		List<User> userList = repo.findAll();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	public ResponseEntity<User> getUserById(int userId) {
		if (!repo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		User user = repo.findById(userId).get();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<User> createUser(User user) {
		if (repo.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException();
		}
		return new ResponseEntity<>(repo.save(user), HttpStatus.CREATED);
	}

	public ResponseEntity<User> saveUser(User user) {
		return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
	}

	public ResponseEntity<User> updateUser(User user) {
		if (!repo.existsById(user.getUserId())) {
			throw new UserNotFoundException();
		}
		return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
	}

	public ResponseEntity<User> deleteUserById(int userId) {
		if(!repo.existsById(userId)){
			throw new UserNotFoundException();
		}
		User user = repo.findById(userId).get();
		repo.deleteById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	public ResponseEntity<User> deleteUser(User user) {
		if(!repo.existsById(user.getUserId())){
			throw new UserNotFoundException();
		}
		repo.delete(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

}
