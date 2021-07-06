package com.athensoft.prototype.error.exceptions;

import com.athensoft.prototype.mvc.entity.Coupon;

public class CouponNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -5102835792025051215L;
	
	public CouponNotFoundException(Coupon coupon) {
		super("Coupon is not found:" + coupon);
	}
	
	public CouponNotFoundException(int couponId) {
		super("Coupon " + couponId + " is not found");
	}

}
