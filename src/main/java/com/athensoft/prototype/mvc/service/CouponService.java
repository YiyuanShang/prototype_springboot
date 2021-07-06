package com.athensoft.prototype.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.error.exceptions.CouponNotFoundException;
import com.athensoft.prototype.mvc.dao.CouponRepository;
import com.athensoft.prototype.mvc.entity.Coupon;

@Service
public class CouponService {
	private final CouponRepository repo;

	public CouponService(CouponRepository repo) {
		this.repo = repo;
	}

	public ResponseEntity<List<Coupon>> getCouponListAll() {
		List<Coupon> couponList = repo.findAll();
		return new ResponseEntity<>(couponList, HttpStatus.OK);
	}

	public ResponseEntity<Coupon> getCouponById(int couponId) {
		if (!repo.existsById(couponId)) {
			throw new CouponNotFoundException(couponId);
		}
		Coupon coupon = repo.findById(couponId).get();
		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

	public ResponseEntity<Coupon> createCoupon(Coupon coupon) {
		return new ResponseEntity<>(repo.save(coupon), HttpStatus.CREATED);
	}

	public ResponseEntity<Coupon> updateCoupon(Coupon coupon) {
		int couponId = coupon.getCouponId();
		if (!repo.existsById(couponId)) {
			throw new CouponNotFoundException(couponId);
		}
		return new ResponseEntity<>(repo.save(coupon), HttpStatus.OK);
	}

	public ResponseEntity<Coupon> deleteCouponById(int couponId) {
		if (!repo.existsById(couponId)) {
			throw new CouponNotFoundException(couponId);
		}
		Coupon coupon = repo.findById(couponId).get();
		repo.deleteById(couponId);
		return new ResponseEntity<>(coupon, HttpStatus.OK);

	}

	public ResponseEntity<Coupon> deleteCoupon(Coupon coupon) {
		int couponId = coupon.getCouponId();
		if (!repo.existsById(couponId)) {
			throw new CouponNotFoundException(couponId);
		}
		repo.delete(coupon);
		return new ResponseEntity<>(coupon, HttpStatus.OK);

	}

	public ResponseEntity<List<Coupon>> createCoupons(List<Coupon> coupons) {
		List<Coupon> createdCoupons = new ArrayList<>();
		for (Coupon coupon: coupons) {
			createdCoupons.add(coupon);
		}
		return new ResponseEntity<>(repo.saveAll(createdCoupons), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Coupon>> updateCoupons(List<Coupon> coupons) {
		List<Coupon> updatedCoupons = new ArrayList<>();
		for (Coupon coupon: coupons) {
			int couponId = coupon.getCouponId();
			if (!repo.existsById(couponId)) {
				throw new CouponNotFoundException(couponId);
			}
			updatedCoupons.add(coupon);
		}
		return new ResponseEntity<>(repo.saveAll(updatedCoupons), HttpStatus.OK);
	}
	
	public ResponseEntity<List<Coupon>> deleteCoupons(List<Coupon> coupons) {
		List<Coupon> deletedCoupons = new ArrayList<>();
		for (Coupon coupon: coupons) {
			int couponId = coupon.getCouponId();
			if (!repo.existsById(couponId)) {
				throw new CouponNotFoundException(couponId);
			}
			deletedCoupons.add(coupon);
		}
		repo.deleteAllInBatch(deletedCoupons);
		return new ResponseEntity<>(deletedCoupons, HttpStatus.OK);
	}

}
