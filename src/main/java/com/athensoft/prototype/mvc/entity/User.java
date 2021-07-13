package com.athensoft.prototype.mvc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "prototype_mvc_multibo_user")
@JsonInclude(Include.NON_EMPTY)
public class User implements Serializable{
	
	private static final long serialVersionUID = 742992239461620390L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	
	private String name;
	
	private int age;
	
	
	@Column(name = "membership")
	private boolean membership;
	
	@OneToMany(targetEntity = UserRelCoupon.class, mappedBy = "user", fetch = FetchType.EAGER)
	private List<Coupon> myCoupons;

	public User() {}
	
	public User(String name, int age, boolean hasMembership) {
		this.name = name;
		this.age = age;
		this.membership = hasMembership;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId =  userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

	public boolean isHasMembership() {
		return membership;
	}

	public void setHasMembership(boolean hasMembership) {
		this.membership = hasMembership;
	}

	

}
