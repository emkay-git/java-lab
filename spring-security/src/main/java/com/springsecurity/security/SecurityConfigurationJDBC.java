package com.springsecurity.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class SecurityConfigurationJDBC extends WebSecurityConfigurerAdapter {

	/** By default it points to embedded data source i.e. h2 db **/
	@Autowired
	public DataSource dataSource;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * 
		 * 
		 * jdbcAuthentication() means we have a database and authentication needs to be
		 * done after fetching the user's id password and auth rules from the data base.
		 * 
		 * We configure the database with the jdbcAuthentication by providing the data
		 * source. If we are using some in memory database like h2, Spring security
		 * framework automatically configures it, by inserting the users inside the
		 * database, and at the time of authentication fetching values from the database
		 * and authenticating then.
		 * 
		 */

//		auth.jdbcAuthentication()
//				/**
//				 * this configures the database with h2 database out of the box with an embedded
//				 * database
//				 **/
//				.dataSource(dataSource)
//				/**
//				 * spring security creates by default a scheme for user and authorities table
//				 * and inserts the user in it. We can change it to have our own schema.
//				 */
//
//				.withDefaultSchema()
//				/**
//				 * Here we are hardcoding the user which is not good for production grade
//				 **/
//				.withUser(User.withUsername("mohit").password("mohit123").roles("USER"))
//				.withUser(User.withUsername("rohit").password("rohit123").roles("ADMIN"));

		/**
		 * creating my own table schema in schema.sql and then inserting data.sql to
		 * insert the data as well. Schema is defined in the format required by spring
		 * security default. But we can also reconfigure our query to get the username
		 * and authority
		 * 
		 */
		auth.jdbcAuthentication().dataSource(dataSource);
		/**
		 * So far saw the default behaviour. Where data source is configured as h2 db
		 * and default schema is automatically prepared by security.
		 * 
		 * we can configure data source in application.properties file we can have
		 * different scehma and prepare our own query, but we need to have username,
		 * password and authorities column in the table It's done as below
		 * 
		 * auth.jdbcAuthentication().usersByUsernameQuery()
		 * auth.jdbcAuthentication().authoritiesByUsernameQuery()
		 * 
		 * we make queries in these.
		 */

	}

	/**
	 * This is to set the password encoder. It's must thing to configure
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
