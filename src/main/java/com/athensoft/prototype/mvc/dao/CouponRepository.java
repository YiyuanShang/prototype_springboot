package com.athensoft.prototype.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athensoft.prototype.mvc.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{

}
