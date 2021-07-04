package com.athensoft.prototype.mvc.service;

import java.util.ArrayList;
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
			throw new UserNotFoundException(userId);
		}
		User user = repo.findById(userId).get();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<User> createUser(User user) {
		if (repo.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException(user);
		}
		return new ResponseEntity<>(repo.save(user), HttpStatus.CREATED);
	}

	public ResponseEntity<User> saveUser(User user) {
		return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
	}

	public ResponseEntity<User> updateUser(User user) {
		int userId = user.getUserId();
		if (!repo.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
	}

	public ResponseEntity<User> deleteUserById(int userId) {
		if(!repo.existsById(userId)){
			throw new UserNotFoundException(userId);
		}
		User user = repo.findById(userId).get();
		repo.deleteById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	public ResponseEntity<User> deleteUser(User user) {
		int userId = user.getUserId();
		if(!repo.existsById(userId)){
			throw new UserNotFoundException(userId);
		}
		repo.delete(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	public ResponseEntity<List<User>> createUsers(List<User> users) {
		List<User> createdUsers = new ArrayList<>();
		for (User user: users) {
			if (repo.existsById(user.getUserId())) {
				throw new UserAlreadyExistsException(user);
			}
			createdUsers.add(user);
		}
		return new ResponseEntity<>(repo.saveAll(createdUsers), HttpStatus.CREATED);
	}

	public ResponseEntity<List<User>> updateUsers(List<User> users) {
		List<User> updatedUsers = new ArrayList<>();
		for (User user: users) {
			int userId = user.getUserId();
			if (!repo.existsById(userId)) {
				throw new UserNotFoundException(userId);
			}
			updatedUsers.add(user);
		}
		return new ResponseEntity<>(repo.saveAll(updatedUsers), HttpStatus.OK);
	}
	
	public ResponseEntity<List<User>> deleteUsers(List<User> users) {
		List<User> deletedUsers = new ArrayList<>();
		for (User user: users) {
			int userId = user.getUserId();
			if (!repo.existsById(userId)) {
				throw new UserNotFoundException(userId);
			}
			deletedUsers.add(user);
		}
		repo.deleteAllInBatch(deletedUsers);
		return new ResponseEntity<>(deletedUsers, HttpStatus.OK);
	}

}
