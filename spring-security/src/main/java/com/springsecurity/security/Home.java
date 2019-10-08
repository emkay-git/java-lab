package com.springsecurity.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@GetMapping("/")	
	public String home() {
		return "<h1>Hi All</h1>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>Congrats if you can read this</h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>You have all access. And only you know it</h1>";
	}
}
