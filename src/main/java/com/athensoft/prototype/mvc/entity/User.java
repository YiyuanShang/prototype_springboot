package com.athensoft.prototype.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "prototype_user")
@JsonInclude(Include.NON_EMPTY)
public class User implements Serializable{
	
	private static final long serialVersionUID = 742992239461620390L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	
	private String name;
	
	private int age;
	
	@Column(columnDefinition = "ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE'")
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column(name = "membership")
	private boolean hasMembership;
	
	@OneToOne
	@JoinColumn(name="membership_id")
	private Membership membership;

	public User() {}
	
	public User(String name, int age, boolean hasMembership) {
		this.name = name;
		this.age = age;
		this.hasMembership = hasMembership;
		this.status = UserStatus.ACTIVE;
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
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public boolean isHasMembership() {
		return hasMembership;
	}

	public void setHasMembership(boolean hasMembership) {
		this.hasMembership = hasMembership;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public UserStatus getUserStatus() {
		return status;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.status = userStatus;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age + ", status=" + status + ", hasMembership="
				+ hasMembership + ", membership=" + membership + "]";
	}

}
