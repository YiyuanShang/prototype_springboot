package com.prototype.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class I18nController {
	
	@RequestMapping("/index")
	public String gotoIndex() {
		return "index";
	}
	
	
	
	
}
