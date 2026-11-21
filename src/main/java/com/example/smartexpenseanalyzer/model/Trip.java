package com.example.smartexpenseanalyzer.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity public class Trip { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne(optional=false) public AppUser user; @Column(nullable=false) public String name; public String destination; public LocalDate startDate; public LocalDate endDate; public Trip(){} }
