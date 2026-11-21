package com.example.smartexpenseanalyzer.config;
import com.example.smartexpenseanalyzer.security.SessionGuard; import org.springframework.context.annotation.Configuration; import org.springframework.web.servlet.config.annotation.*;
@Configuration public class SecurityConfig implements WebMvcConfigurer { private final SessionGuard guard; public SecurityConfig(SessionGuard guard){this.guard=guard;} @Override public void addInterceptors(InterceptorRegistry registry){registry.addInterceptor(guard).addPathPatterns("/api/**");} }
