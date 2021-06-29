package com.prototype.thymeleaf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prototype.thymeleaf.entity.User;
import com.prototype.thymeleaf.service.UserRepository;

@Configuration
class LoadDatabase {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
    	LOGGER.info("Preloading " + repository.save(new User("John", 20, true)));
    	LOGGER.info("Preloading " + repository.save(new User("Jane", 26, false)));
    	LOGGER.info("Preloading " + repository.save(new User("Doe", 35, false)));
    	LOGGER.info("Preloading " + repository.save(new User("Lisa", 46, true)));
    	LOGGER.info("Preloading " + repository.save(new User("Ann", 41, false)));
    };
  }
}