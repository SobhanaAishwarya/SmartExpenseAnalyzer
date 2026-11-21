package com.example.smartexpenseanalyzer.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.*;
@Entity public class Expense { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne(optional=false) public AppUser user; @ManyToOne public Trip trip; @Column(nullable=false) public String category; @Column(nullable=false) public String description; @Column(nullable=false,precision=12,scale=2) public BigDecimal amount; @Column(nullable=false) public LocalDate date; public LocalDateTime createdAt=LocalDateTime.now(); public Expense(){} }
