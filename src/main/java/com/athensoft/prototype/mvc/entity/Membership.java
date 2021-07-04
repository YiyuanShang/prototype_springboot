package com.athensoft.prototype.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prototype_membership")
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int membershipId;

	private String description;
	
	@Enumerated(EnumType.STRING)
	private MembershipType type;

	public int getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MembershipType getType() {
		return type;
	}

	public void setType(MembershipType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Membership [membershipId=" + membershipId + ", description=" + description + ", type=" + type + "]";
	}
	
	
	
}
