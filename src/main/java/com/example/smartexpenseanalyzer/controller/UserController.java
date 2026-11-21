package com.example.smartexpenseanalyzer.controller;
import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/users") public class UserController { private final com.example.smartexpenseanalyzer.service.ApiFacade app; public UserController(com.example.smartexpenseanalyzer.service.ApiFacade app){this.app=app;} @GetMapping("/me") public Map<String,Object> current(HttpSession session){return app.current(session);} }
