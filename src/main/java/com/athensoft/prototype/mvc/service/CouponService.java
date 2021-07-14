package com.athensoft.prototype.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.error.exceptions.CouponAlreadyExistsException;
import com.athensoft.prototype.error.exceptions.CouponNotFoundException;
import com.athensoft.prototype.mvc.dao.CouponRepository;
import com.athensoft.prototype.mvc.dao.UserCouponRepository;
import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.entity.UserRelCoupon;

@Service
public class CouponService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponService.class);

	private final CouponRepository couponRepo;
	private final UserCouponRepository relRepo;

	public CouponService(CouponRepository couponRepo, UserCouponRepository relRepo) {
		this.couponRepo = couponRepo;
		this.relRepo = relRepo;
	}

	public ResponseEntity<List<Coupon>> getCouponListAll() {
		List<Coupon> couponList = couponRepo.findAll();
		return new ResponseEntity<>(couponList, HttpStatus.OK);
	}

	public List<Coupon> getCouponListByUser(User user) {
		List<UserRelCoupon> relList = relRepo.findByUser(user);

		List<Coupon> couponList = new ArrayList<>();
		for (UserRelCoupon rel : relList) {
			for (int i = 0; i < rel.getCouponNum(); i++) {
				couponList.add(rel.getCoupon());
			}
		}

		LOGGER.debug("coupon list:" + couponList);
		return couponList;
	}

	public List<Coupon> getCouponListByUserId(int userId) {
		List<UserRelCoupon> relList = relRepo.findByUser_userId(userId);

		List<Coupon> couponList = new ArrayList<>();
		for (UserRelCoupon rel : relList) {
			for (int i = 0; i < rel.getCouponNum(); i++) {
				couponList.add(rel.getCoupon());
			}
		}

		LOGGER.debug("coupon list:" + couponList);
		return couponList;
	}

	public ResponseEntity<Coupon> getCouponById(int couponId) {
		checkCouponNotFoundException(couponId);
		Coupon coupon = couponRepo.findById(couponId).get();
		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

	public ResponseEntity<Coupon> createCoupon(Coupon coupon) {
		checkCouponAlreadyExistsException(coupon.getCouponId());
		return new ResponseEntity<>(couponRepo.save(coupon), HttpStatus.CREATED);
	}

	public ResponseEntity<Coupon> updateCoupon(Coupon coupon) {
		checkCouponNotFoundException(coupon.getCouponId());
		return new ResponseEntity<>(couponRepo.save(coupon), HttpStatus.OK);
	}

	public ResponseEntity<Coupon> deleteCouponById(int couponId) {
		checkCouponNotFoundException(couponId);
		Coupon coupon = couponRepo.findById(couponId).get();
		couponRepo.deleteById(couponId);
		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

	public ResponseEntity<Coupon> deleteCoupon(Coupon coupon) {
		int couponId = coupon.getCouponId();
		checkCouponNotFoundException(couponId);
		couponRepo.delete(coupon);

		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

	public ResponseEntity<List<Coupon>> createCoupons(List<Coupon> coupons) {
		List<Coupon> createdCoupons = new ArrayList<>();
		for (Coupon coupon : coupons) {
			checkCouponAlreadyExistsException(coupon.getCouponId());
			createdCoupons.add(coupon);
		}
		return new ResponseEntity<>(couponRepo.saveAll(createdCoupons), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Coupon>> updateCoupons(List<Coupon> coupons) {
		List<Coupon> updatedCoupons = new ArrayList<>();
		for (Coupon coupon : coupons) {
			checkCouponNotFoundException(coupon.getCouponId());
			updatedCoupons.add(coupon);

		}
		return new ResponseEntity<>(couponRepo.saveAll(updatedCoupons), HttpStatus.OK);
	}

	public ResponseEntity<List<Coupon>> deleteCoupons(List<Coupon> coupons) {
		List<Coupon> deletedCoupons = new ArrayList<>();
		for (Coupon coupon : coupons) {
			checkCouponNotFoundException(coupon.getCouponId());
			deletedCoupons.add(coupon);

		}
		couponRepo.deleteAllInBatch(deletedCoupons);
		return new ResponseEntity<>(deletedCoupons, HttpStatus.OK);
	}

	void checkCouponNotFoundException(int couponId) {
		if (!couponRepo.existsById(couponId)) {
			throw new CouponNotFoundException(couponId);
		}
	}

	void checkCouponAlreadyExistsException(int couponId) {
		if (couponRepo.existsById(couponId)) {
			throw new CouponAlreadyExistsException(couponId);
		}
	}

}