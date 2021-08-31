package com.athensoft.prototype.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class I18nController {
	
	@RequestMapping({"/index", "/{lang}/index"})
	public String gotoIndex(@PathVariable(required=false) String lang) {
		return "/index";
	}
	
	
	
	
}
