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

	public UserService(UserRepository repo, UserCouponRepository relRepo, CouponService couponService,
			UserRelCouponService relService) {
		this.userRepo = repo;
		this.relRepo = relRepo;
		this.couponService = couponService;
		this.relService = relService;
	}

	public List<User> getUserListAll() {
		List<User> userList = userRepo.findAll();

		// for every user, find coupon list by user id
//		userList.forEach(user -> user.setMyCoupons(couponService.getCouponListByUserId(user.getUserId())));

		// for every user, find coupon list by user
		userList.forEach(user -> user.setMyCoupons(couponService.getCouponListByUser(user)));

		return userList;
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

	public List<User> getUserListByCouponId(int couponId) {
		List<UserRelCoupon> relList = relRepo.findByCoupon_couponId(couponId);

		List<User> userList = new ArrayList<>();
		relList.forEach(rel -> userList.add(rel.getUser()));

		LOGGER.debug("user list:" + userList);
		return userList;
	}

	public User createUser(User user) {
		LOGGER.debug("create user:" + user);

		checkUserAlreadyExistsException(user.getUserId());
		User createdUser = userRepo.save(user);

		List<Coupon> myCoupons = user.getMyCoupons();
		if (!myCoupons.isEmpty()) {
			for (Coupon myCoupon : myCoupons) {
				couponService.checkCouponNotFoundException(myCoupon.getCouponId());

				// coupon number is 1
				relService.createUserRelCoupon(createdUser, myCoupon, 1);
			}
		}

		return getUserById(createdUser.getUserId());
	}

	public User updateUser(User user) {
		checkUserNotFoundException(user.getUserId());
		LOGGER.debug("update user:" + user);
		List<Coupon> myCoupons = user.getMyCoupons();
		
		user.setMyCoupons(null);
		User updatedUser = userRepo.save(user);
		
		LOGGER.debug("update coupons");
		LOGGER.debug("myCoupons size:" + myCoupons.size());
		
		
		if (!myCoupons.isEmpty()) {
			for (Coupon myCoupon : myCoupons) {
				couponService.checkCouponNotFoundException(myCoupon.getCouponId());

				// coupon number is 1
				relService.updateUserRelCoupon(user, myCoupon, 1);
			}
		}
		
		
		return getUserById(updatedUser.getUserId());
	}
	
	@Transactional
	public User deleteUserById(int userId) {
		checkUserNotFoundException(userId);
		User user = getUserById(userId);
		
		relService.deleteUserRelCoupon(userId);
		userRepo.deleteById(userId);
		return user;
	}

	public User deleteUser(User user) {
		int userId = user.getUserId();
		checkUserNotFoundException(userId);
		
		relService.deleteUserRelCoupon(userId);
		userRepo.delete(user);
		return user;
	}

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
