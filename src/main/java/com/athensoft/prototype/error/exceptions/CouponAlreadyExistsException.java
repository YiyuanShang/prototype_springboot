package com.athensoft.prototype.error.exceptions;

import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;

public class CouponAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -694504633335055824L;

	public CouponAlreadyExistsException(Coupon coupon) {
		super("Coupon " + coupon.getCouponId() + ": " + coupon.getDescription() + " (starts from " + coupon.getStartDate() + " expires at " + coupon.getExpirationDate() + ") already exists!");
	}
	
	public CouponAlreadyExistsException(int couponId) {
		super("Coupon " + couponId + " already exists!");
	}
	
	public CouponAlreadyExistsException(String message) {
		super(message);
	}

}
