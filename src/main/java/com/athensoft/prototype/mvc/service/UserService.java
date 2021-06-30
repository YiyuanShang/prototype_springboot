package com.athensoft.prototype.mvc.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;

@Service
@Transactional
public class UserService {
    private final UserRepository repo;
    
    public UserService(UserRepository repo) {
    	this.repo = repo;
    }
     
    public ResponseEntity<List<User>> getUserListAll() {
        List<User> userList = repo.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
     
    public ResponseEntity<User> getUserById(int userId) {
	    User user = repo.findById(userId).get();
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public ResponseEntity<User> saveUser(User user) {
        return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
    }
     
    public ResponseEntity<String> deleteUserById(int userId) {
    	repo.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
    public ResponseEntity<String> deleteUser(User user) {
    	repo.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

}
