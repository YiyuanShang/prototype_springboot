package com.athensoft.prototype.mvc.service;

import org.springframework.stereotype.Service;

import com.athensoft.prototype.mvc.dao.UserCouponRepository;
import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.entity.UserCouponId;
import com.athensoft.prototype.mvc.entity.UserRelCoupon;

@Service
public class UserRelCouponService {
	private final UserCouponRepository relRepo;
	
	public UserRelCouponService(UserCouponRepository relRepo) {
		this.relRepo = relRepo;
	}

	public void saveUserRelCoupon(User user, Coupon coupon, int couponNum) {
		// if the relation exists, increment coupon number
		if (relRepo.existsByUserAndCoupon(user, coupon)) {
			UserRelCoupon rel = relRepo.findByUserAndCoupon(user, coupon);
			couponNum += rel.getCouponNum();
		}
		// else, create the relation
		relRepo.save(new UserRelCoupon(user, coupon, couponNum));
	}
	
}