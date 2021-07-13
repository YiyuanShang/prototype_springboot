package com.athensoft.prototype.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athensoft.prototype.mvc.entity.Coupon;
import com.athensoft.prototype.mvc.entity.User;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

}
