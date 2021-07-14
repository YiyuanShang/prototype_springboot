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

	public void createOrIncUserRelCoupon(User user, Coupon coupon, int couponNum) {
		// if the relation exists, increment coupon number
		if (relRepo.existsByUserAndCoupon(user, coupon)) {
			UserRelCoupon rel = relRepo.findByUserAndCoupon(user, coupon);
			couponNum += rel.getCouponNum();
		}
		// else, create the relation
		relRepo.save(new UserRelCoupon(user, coupon, couponNum));
	}
	
	public void createUserRelCoupon(User user, Coupon coupon, int couponNum) {
		relRepo.save(new UserRelCoupon(user, coupon, couponNum));
	}
	
	public void updateUserRelCoupon(User user, Coupon coupon, int couponNum) {
		UserRelCoupon rel = relRepo.findByUserAndCoupon(user, coupon);
		rel.setCouponNum(couponNum);
		relRepo.save(rel);
	}
	
	public void deleteUserRelCoupon(User user, Coupon coupon) {
		relRepo.deleteByUserAndCoupon(user, coupon);
	}
	
	public void deleteUserRelCoupon(int userId) {
		relRepo.deleteByUser_userId(userId);
	}
	
}