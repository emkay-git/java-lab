package com.springsecurity.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.security.models.User;

public interface MyUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUsername(String username);
}
