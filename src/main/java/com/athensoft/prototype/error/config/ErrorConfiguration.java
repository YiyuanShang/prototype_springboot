package com.athensoft.prototype.error.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfiguration {
	@Bean
	public ErrorViewResolver errorViewResolver() {
		return new MyErrorViewResolver();
	}

}
