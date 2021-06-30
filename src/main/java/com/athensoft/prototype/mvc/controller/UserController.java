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
	
	@GetMapping("/user/edit/{userId}")
	public String showUpdateForm(@PathVariable("userId") int id, Model model) {
	    User user = repository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "user/user_edit";
	}
	
	@PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/user_create";
        }
        repository.save(user);
        return "redirect:/user/list";
    }
	
	@PostMapping("/user/update/{userId}")
	public String updateUser(@PathVariable("userId") int userId, @Valid User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        user.setUserId(userId);
	        return "user/user_edit";
	    }
	   
	    repository.save(user);
	    return "redirect:/index";
	}
	

}
