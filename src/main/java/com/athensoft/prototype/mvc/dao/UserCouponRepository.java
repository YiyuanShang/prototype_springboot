package com.athensoft.prototype.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athensoft.prototype.mvc.entity.UserCouponId;
import com.athensoft.prototype.mvc.entity.UserRelCoupon;

public interface UserCouponRepository extends JpaRepository<UserRelCoupon, UserCouponId>{

}
