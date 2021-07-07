package com.athensoft.prototype.mvc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCouponId implements Serializable{
	private static final long serialVersionUID = -8044124390788541293L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "coupon_id")
	private int couponId;

	public UserCouponId() {}
	
	public UserCouponId(int userId, int couponId) {
		this.userId = userId;
		this.couponId = couponId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, couponId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserCouponId))
			return false;
		UserCouponId userCouponId = (UserCouponId) obj;
		return Objects.equals(this.userId, userCouponId.userId) 
				&& Objects.equals(this.couponId, userCouponId.couponId);
	}
	
	
	

}
