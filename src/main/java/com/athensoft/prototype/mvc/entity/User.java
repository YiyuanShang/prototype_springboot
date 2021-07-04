package com.athensoft.prototype.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private boolean membership;
	
	@Column(nullable = true)
	private Integer membership_id;

	public User() {}
	
	public User(String name, int age, boolean membership) {
		this.name = name;
		this.age = age;
		this.membership = membership;
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
	public boolean isMembership() {
		return membership;
	}
	public void setMembership(boolean membership) {
		this.membership = membership;
	}
	
	public UserStatus getUserStatus() {
		return status;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.status = userStatus;
	}

	public int getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(int membership_id) {
		this.membership_id = membership_id;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age + ", status=" + status + ", membership="
				+ membership + ", membership_id=" + membership_id + "]";
	}

	
	
	

	
	
	
	
	

}
