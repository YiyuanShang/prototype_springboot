package com.athensoft.prototype.thymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.athensoft.prototype.thymeleaf.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
