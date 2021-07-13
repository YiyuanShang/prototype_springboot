package com.athensoft.prototype.mvc.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "prototype_mvc_multibo_user_rel_coupon")
public class UserRelCoupon {
	@EmbeddedId
	UserCouponId userCouponId;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToOne
	@MapsId("couponId")
	@JoinColumn(name = "coupon_id")
	Coupon coupon;

	@Column(name = "coupon_number")
	int couponNum;

	public UserRelCoupon() {}
	
	public UserRelCoupon(User user, Coupon coupon, int couponNum) {
		this.userCouponId = new UserCouponId(user.getUserId(), coupon.getCouponId());
		this.user = user;
		this.coupon = coupon;
		this.couponNum = couponNum;
	}

	public UserCouponId getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(UserCouponId userCouponId) {
		this.userCouponId = userCouponId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public int getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(int couponNum) {
		this.couponNum = couponNum;
	}
	
	
}
