package com.athensoft.prototype.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.error.exceptions.UserAlreadyExistsException;
import com.athensoft.prototype.error.exceptions.UserNotFoundException;
import com.athensoft.prototype.mvc.controller.UserController;
import com.athensoft.prototype.mvc.dao.CouponRepository;
import com.athensoft.prototype.mvc.dao.UserCouponRepository;
import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepo;
	private final UserCouponRepository userCouponRepo;

	public UserService(UserRepository repo, UserCouponRepository userCouponRepo) {
		this.userRepo = repo;
		this.userCouponRepo = userCouponRepo;
	}

	public ResponseEntity<List<User>> getUserListAll() {
		List<User> userList = userRepo.findAll();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	public ResponseEntity<User> getUserById(int userId) {
		if (!userRepo.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		User user = userRepo.findById(userId).get();
		LOGGER.debug("user:" + user);
	
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

//	public ResponseEntity<Map<String, Object>> createUser(Map<String, Object> map) {
//		
//		ObjectMapper objMapper = new ObjectMapper();
//		User user = objMapper.convertValue(map.get("user"), new TypeReference<User>() {});
//		Membership membership = objMapper.convertValue(map.get("membership"), new TypeReference<Membership>() {});
//		
//		LOGGER.debug("user:" + user);
//		LOGGER.debug("membership:" + membership);
//		
//		if (userRepo.existsById(user.getUserId())) {
//			throw new UserAlreadyExistsException(user);
//		}
//		
//		if (!userRepo.existsById(membership.getMembershipId())) {
//			membership = userCouponRepo.save(membership);
//			map.replace("membership", membership);
//		}
//		user.setMembership(membership);
//		map.replace("user", userRepo.save(user));
//		return new ResponseEntity<>(map, HttpStatus.CREATED);
//	}
//
//	public ResponseEntity<User> saveUser(User user) {
//		return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
//	}
//
//	public ResponseEntity<Map<String, Object>> updateUser(Map<String, Object> map) {
//		ObjectMapper objMapper = new ObjectMapper();
//		User user = objMapper.convertValue(map.get("user"), new TypeReference<User>() {});
//		Membership membership = objMapper.convertValue(map.get("membership"), new TypeReference<Membership>() {});
//		
//		LOGGER.debug("user:" + user);
//		LOGGER.debug("membership:" + membership);
//		
//		int userId = user.getUserId();
//		if (!userRepo.existsById(userId)) {
//			throw new UserNotFoundException(userId);
//		}
//		
//		if (!userRepo.existsById(membership.getMembershipId())) {
//			membership = userCouponRepo.save(membership);
//			map.replace("membership", membership);
//		}
//		user.setMembership(membership);
//		map.replace("user", userRepo.save(user));
//		return new ResponseEntity<>(map, HttpStatus.OK);
//	}
//
//	public ResponseEntity<User> deleteUserById(int userId) {
//		if(!userRepo.existsById(userId)){
//			throw new UserNotFoundException(userId);
//		}
//		User user = userRepo.findById(userId).get();
//		userRepo.deleteById(userId);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//
//	}
//
//	public ResponseEntity<User> deleteUser(User user) {
//		int userId = user.getUserId();
//		if(!userRepo.existsById(userId)){
//			throw new UserNotFoundException(userId);
//		}
//		userRepo.delete(user);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//
//	}
}
