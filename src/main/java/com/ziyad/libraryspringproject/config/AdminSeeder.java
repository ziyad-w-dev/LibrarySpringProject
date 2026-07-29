package com.ziyad.libraryspringproject.config;

import com.ziyad.libraryspringproject.domain.entity.Role;
import com.ziyad.libraryspringproject.domain.entity.User;
import com.ziyad.libraryspringproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminSeeder implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${app.admin.userName}")
    private String adminUsername;
    @Value("${app.admin.email}")
    private String email;
    @Value("${app.admin.password}")
    private String password;

    public void seedTheAdmin(){
        if(userRepository.existsByRole(Role.ADMIN)){
           return;
        }
        User admin = new User();
        admin.setRole(Role.ADMIN);
        admin.setUserName(adminUsername);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        userRepository.save(admin);
    }

    @Override
    public void run(String... args) throws Exception {
        seedTheAdmin();
    }
}
