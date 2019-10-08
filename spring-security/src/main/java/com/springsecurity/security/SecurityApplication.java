package com.springsecurity.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springsecurity.security.repositories.MyUserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = MyUserRepository.class)
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
