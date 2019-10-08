package com.springsecurity.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.security.models.CustomUserDetails;
import com.springsecurity.security.models.User;
import com.springsecurity.security.repositories.MyUserRepository;

@Service
public class JPAUserDetailsService implements UserDetailsService {

	@Autowired
	MyUserRepository myUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = myUserRepository.findUserByUsername(username);
		System.out.println(username);
		user.orElseThrow(() -> new UsernameNotFoundException("REGISTER FIRST"));
		return user.map(CustomUserDetails::new).get();

	}

}
