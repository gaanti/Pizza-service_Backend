package com.example.pizza;

import com.example.pizza.admin.security.user.JwtUser;
import com.example.pizza.admin.security.user.JwtUserService;
import com.example.pizza.admin.security.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitUsers implements CommandLineRunner {

    private final JwtUserService jwtUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!jwtUserService.findByEmail("admin@test.com").isPresent()) {
            Set<Role> rolesSet = new HashSet<>();
            rolesSet.add(Role.ROLE_USER);
            rolesSet.add(Role.ROLE_ADMIN);
            JwtUser u = jwtUserService.save(JwtUser.builder()
                    .userName("Admin")
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(rolesSet)
                    .build());
            u.setEnabled(true);
            jwtUserService.save(u);
        }
        if (!jwtUserService.findByEmail("user@test.com").isPresent()) {
            Set<Role> rolesSet = new HashSet<>();
            rolesSet.add(Role.ROLE_USER);
            JwtUser u = jwtUserService.save(JwtUser.builder()
                    .userName("User")
                    .email("user@test.com")
                    .password(passwordEncoder.encode("test123"))
                    .role(rolesSet)
                    .build());
            u.setEnabled(true);
            jwtUserService.save(u);
        }
    }

}
