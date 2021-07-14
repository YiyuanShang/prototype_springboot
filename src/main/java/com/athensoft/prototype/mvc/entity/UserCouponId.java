package com.athensoft.prototype.mvc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCouponId implements Serializable{
	private static final long serialVersionUID = -8044124390788541293L;
	
	private int userId;
	
	private int couponId;

	public UserCouponId() {}
	
	public UserCouponId(int userId, int couponId) {
		this.userId = userId;
		this.couponId = couponId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
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
