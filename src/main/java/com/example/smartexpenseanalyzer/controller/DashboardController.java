package com.example.smartexpenseanalyzer.controller;
import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/dashboard") public class DashboardController { private final com.example.smartexpenseanalyzer.service.ApiFacade app; public DashboardController(com.example.smartexpenseanalyzer.service.ApiFacade app){this.app=app;} @GetMapping public Map<String,Object> summary(HttpSession session){return app.dashboard(session);} }
