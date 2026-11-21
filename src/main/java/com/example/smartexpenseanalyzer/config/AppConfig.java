package com.example.smartexpenseanalyzer.config;
import org.springframework.context.annotation.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; @Configuration public class AppConfig { @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();} }
