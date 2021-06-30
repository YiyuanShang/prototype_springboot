package com.athensoft.prototype.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.athensoft.prototype.mvc.entity.User;
import com.athensoft.prototype.mvc.dao.UserRepository;


@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final UserRepository repository;

	public UserController(UserRepository repository) {
	    this.repository = repository;
	  }
	
	@GetMapping("/user/signup")
	public String gotoUserSignUp(User user) {
		return "user/user_create";
	}
	
	@GetMapping("/user/list")
	public String gotoUserList(Model model) {
		
		List<User> userList = repository.findAll();
		LOGGER.debug("returning user list:" + userList);
		model.addAttribute("userList", userList);
		return "user/user_list";
	}
	
	@GetMapping("/user/edit/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		LOGGER.debug("entering showUpdateForm id:" + id);
		int userId = Integer.valueOf(id);
	    User user = repository.findById(userId)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "user/user_edit";
	}
	
	@GetMapping("/user/delete/{id}")
	public String showDeleteForm(@PathVariable("id") String id, Model model) {
		LOGGER.debug("entering showDeleteForm id:" + id);
		int userId = Integer.valueOf(id);
	    User user = repository.findById(userId)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "user/user_delete";
	}
	
	@PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/user_create";
        }
        repository.save(user);
        return "redirect:/user/list";
    }
	
	@PostMapping("/user/update")
	public String updateUser(@Valid @ModelAttribute("user") User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
			int userId = user.getUserId();
	        user.setUserId(userId);
	        return "user/user_edit";
	    }
	    
	    LOGGER.info("update user:" + user);
	    repository.save(user);
	    return "redirect:/user/list";
	}
	
	@PostMapping("/user/delete")
	public String deleteUser(@Valid @ModelAttribute("user") User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
			int userId = user.getUserId();
	        user.setUserId(userId);
	        return "user/user_delete";
	    }
	    
	    LOGGER.info("delete user:" + user);
	    repository.delete(user);
	    return "redirect:/user/list";
	}
	

}
