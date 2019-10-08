package com.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Providing list of users and giving them authentication and roles.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("mohit").password("mohit123").roles("USER").and().withUser("rohit")
				.password("rohit123").roles("ADMIN");
	}

	/**
	 * This is to set the password encoder. It's must thing to configure.
	 * 
	 * inMemoryAuthentication() means there is no database and authentication will
	 * be done in memory.
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user", "USER")
				.hasAnyRole("USER", "ADMIN").antMatchers("/").permitAll().and().formLogin();
	}

}
