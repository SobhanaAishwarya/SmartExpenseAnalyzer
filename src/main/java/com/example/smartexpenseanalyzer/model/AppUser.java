package com.example.smartexpenseanalyzer.model;
import jakarta.persistence.*;
@Entity @Table(name="app_users") public class AppUser { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(nullable=false,unique=true) public String email; @Column(nullable=false) public String name; @Column(nullable=false) public String passwordHash; @Column(nullable=false) public String role="USER"; public java.math.BigDecimal budget=java.math.BigDecimal.ZERO; public AppUser(){} }
