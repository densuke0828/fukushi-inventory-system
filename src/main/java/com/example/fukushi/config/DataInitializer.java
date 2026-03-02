package com.example.fukushi.config;

import com.example.fukushi.repository.UserRepository;
import groovyjarjarpicocli.CommandLine;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;
}
