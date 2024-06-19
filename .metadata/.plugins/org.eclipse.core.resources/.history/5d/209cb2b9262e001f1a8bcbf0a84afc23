package com.example.UserRegistrationApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = bCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
