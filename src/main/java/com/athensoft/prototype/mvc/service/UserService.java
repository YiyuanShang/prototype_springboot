package com.athensoft.prototype.mvc.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athensoft.prototype.mvc.dao.UserRepository;
import com.athensoft.prototype.mvc.entity.User;

@Service
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
    	try {
	    User user = repo.findById(userId).get();
	    return new ResponseEntity<>(user, HttpStatus.OK);
    	}catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      
	}

	public ResponseEntity<User> saveUser(User user) {
        return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
    }
     
    public ResponseEntity<String> deleteUserById(int userId) {
    	try {
			repo.deleteById(userId);
			return new ResponseEntity<>(HttpStatus.OK);
    	} catch(EmptyResultDataAccessException e) {
    		return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    	} 
        
    }
    
    public ResponseEntity<String> deleteUser(User user) {
    	repo.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

}
