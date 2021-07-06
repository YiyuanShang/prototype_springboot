package com.athensoft.prototype.mvc.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athensoft.prototype.mvc.dao.CouponRepository;
import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.service.CouponService;



@Controller
public class CouponController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);
	
	private final CouponService couponService;
	
	public CouponController(CouponService couponService) {
		this.couponService = couponService;
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<List<Coupon>> getCouponListAll() {
		return couponService.getCouponListAll();
	}
	
	@GetMapping("/coupons/{couponId}")
	@ResponseBody
	public ResponseEntity<Coupon> getCouponById(@PathVariable int couponId) {
		return couponService.getCouponById(couponId);
	}
	
	
	@PostMapping("/coupons")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
		LOGGER.debug("creating coupon:" + coupon);
        return couponService.createCoupon(coupon);
    }
	
	@PutMapping("/coupons")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon) {
		LOGGER.debug("updating coupon:" + coupon);
        return couponService.updateCoupon(coupon);
    }
	
	@DeleteMapping("/coupons/{couponId}")
    public ResponseEntity<Coupon> deleteCouponById(@PathVariable int couponId) {
		LOGGER.debug("deleting coupon id:" + couponId);
        return couponService.deleteCouponById(couponId);
    }
	
	@DeleteMapping("/coupons")
    public ResponseEntity<Coupon> deleteCoupon(@RequestBody Coupon coupon) {
		LOGGER.debug("deleting coupon:" + coupon);
        return couponService.deleteCoupon(coupon);
    }
	
	@PostMapping("/coupons/batch")
	public ResponseEntity<List<Coupon>> createCoupons(@RequestBody List<Coupon> coupons) {
		LOGGER.debug("creating coupons:" + coupons);
        return couponService.createCoupons(coupons);
    }
	
	@PutMapping("/coupons/batch")
	public ResponseEntity<List<Coupon>> updateCoupons(@RequestBody List<Coupon> coupons) {
		LOGGER.debug("updating coupons:" + coupons);
        return couponService.updateCoupons(coupons);
    }
	
	@DeleteMapping("/coupons/batch")
	public ResponseEntity<List<Coupon>> deleteCoupons(@RequestBody List<Coupon> coupons) {
		LOGGER.debug("deleting coupons:" + coupons);
        return couponService.deleteCoupons(coupons);
    }

}
