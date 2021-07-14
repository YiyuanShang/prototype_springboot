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
import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.entity.UserRelCoupon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private final UserRelCouponService relService;
	private final CouponService couponService;
	
	private final UserRepository userRepo;
	private final UserCouponRepository relRepo;

	public UserService(UserRepository repo, UserCouponRepository relRepo, CouponService couponService, UserRelCouponService relService) {
		this.userRepo = repo;
		this.relRepo = relRepo;
		this.couponService = couponService;
		this.relService = relService;
	}

	public ResponseEntity<List<User>> getUserListAll() {
		List<User> userList = userRepo.findAll();
		
		// for every user, find coupon list by user id
//		userList.forEach(user -> user.setMyCoupons(couponService.getCouponListByUserId(user.getUserId())));
		
		// for every user, find coupon list by user
		userList.forEach(user -> user.setMyCoupons(couponService.getCouponListByUser(user)));
		
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	public User getUserById(int userId) {
		if (!userRepo.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		User user = userRepo.findById(userId).get();
		// find coupon list by user
		user.setMyCoupons(couponService.getCouponListByUser(user));
		
		LOGGER.debug("user:" + user);
		return user;
	}

	public List<User> getUserListByCouponId(int couponId){
		List<UserRelCoupon> relList = relRepo.findByCoupon_couponId(couponId);
		
		List<User> userList = new ArrayList<>();
		relList.forEach(rel -> userList.add(rel.getUser()));
		
		LOGGER.debug("user list:" + userList);
		return userList;
	}
	
	
	
	
	public User createUser(User user) {
		LOGGER.debug("create user:" + user);
		
		if (userRepo.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException(user);
		}
		
		User createdUser = userRepo.save(user);
		
		List<Coupon> myCoupons = user.getMyCoupons();
		if (!myCoupons.isEmpty()) {
			for (Coupon myCoupon : myCoupons) {
				couponService.checkCouponNotFoundException(myCoupon.getCouponId());
				
				// coupon number is 1
				relService.saveUserRelCoupon(createdUser, myCoupon, 1);
			}
		}
		
		return getUserById(createdUser.getUserId());
	}

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
	
	void checkUserNotFoundException(int userId) {
		if (!userRepo.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
	}
	
	void checkUserAlreadyExistsException(int userId) {
		if (userRepo.existsById(userId)) {
			User user = userRepo.findById(userId).get();
			throw new UserAlreadyExistsException(user);
		}
	}
}
