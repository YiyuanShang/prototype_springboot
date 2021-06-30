package com.athensoft.prototype.mvc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		try {
			return repo.findById(userId).get();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("User does not exist");
		}
	}

	public User saveUser(User user) {
		return repo.save(user);
	}

	public String deleteUserById(int userId) {
		try {
			repo.deleteById(userId);
			return "Success";
		} catch (EmptyResultDataAccessException e) {
			return "User does not exist";
		}

	}

	public String deleteUser(User user) {
		repo.delete(user);
		return "Success";

	}

}
