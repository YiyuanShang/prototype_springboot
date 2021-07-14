package com.athensoft.prototype.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.entity.UserCouponId;
import com.athensoft.prototype.mvc.entity.UserRelCoupon;

@Repository
public interface UserCouponRepository extends JpaRepository<UserRelCoupon, UserCouponId>{
	List<UserRelCoupon> findByUser(User user);
	
	List<UserRelCoupon> findByUser_userId(int userId);
	
	List<UserRelCoupon> findByCoupon(Coupon coupon);
	
	List<UserRelCoupon> findByCoupon_couponId(int couponId);

	UserRelCoupon findByUserAndCoupon(User user, Coupon coupon);
	
	boolean existsByUserAndCoupon(User user, Coupon coupon);
	
	void deleteByUserAndCoupon(User user, Coupon coupon);
	
	void deleteByUser_userId(int userId);
}
