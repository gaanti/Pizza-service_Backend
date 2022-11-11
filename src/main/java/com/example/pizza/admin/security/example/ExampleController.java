package com.example.pizza.admin.security.example;

import com.example.pizza.admin.security.user.JwtUser;
import com.example.pizza.admin.security.user.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @Autowired
    JwtUserService jwtUserRepository;


	@GetMapping("/user")
	public ExampleMessage userEndpoint() {
		return new ExampleMessage("Hello user!");
	}

	@GetMapping("/login")
	public ExampleMessage loginEndpoint() {
		return new ExampleMessage("!");
	}

	@GetMapping("/admin")
	public ExampleMessage adminEndpoint() {
		return new ExampleMessage("Hello admin!");
	}

	@GetMapping("/check-db-data")
	public JwtUser checkDbData(@RequestParam(value = "email") String email) {
		return jwtUserRepository.getByEmail(email);
	}


}
