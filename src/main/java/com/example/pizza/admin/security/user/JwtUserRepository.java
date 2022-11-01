package com.example.pizza.admin.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtUserRepository extends JpaRepository<JwtUser, Long> {
	Optional<JwtUser> findJwtUserByUserName(String userName);
	Optional<JwtUser> findJwtUserByEmail(String email);
}
