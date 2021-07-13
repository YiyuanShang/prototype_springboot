package com.athensoft.prototype.mvc.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prototype_mvc_multibo_coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private int couponId;

	private String description;

	private Date startDate;

	private Date expirationDate;
	
	@OneToMany(targetEntity = UserRelCoupon.class, mappedBy = "coupon", fetch = FetchType.EAGER)
	private List<User> owners;
	
	public Coupon() {
	}

	public Coupon(String description, Date startDate, Date expirationDate) {
		this.description = description;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", description=" + description + ", startDate=" + startDate
				+ ", expirationDate=" + expirationDate + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Coupon))
			return false;
		Coupon coupon = (Coupon) o;
		return Objects.equals(this.description, coupon.description) 
				&& Objects.equals(this.startDate, coupon.startDate)
				&& Objects.equals(this.expirationDate, coupon.expirationDate);
	}
	
	@Override
	  public int hashCode() {
	    return Objects.hash(this.description, this.startDate, this.expirationDate);
	  }

}
