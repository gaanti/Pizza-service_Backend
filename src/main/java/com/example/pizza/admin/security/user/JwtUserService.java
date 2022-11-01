package com.example.pizza.admin.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserService {
	private final JwtUserRepository jwtUserRepository;
	public JwtUser save (JwtUser user) {
		return jwtUserRepository.save(user);
	}
	public Optional<JwtUser> findByEmail (String email) {
		return jwtUserRepository.findJwtUserByEmail(email);
	}
	public JwtUser getByEmail(String email){
		return jwtUserRepository.findJwtUserByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
	public JwtUser getByUsername(String userName){
		return jwtUserRepository.findJwtUserByUserName(userName)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
}
