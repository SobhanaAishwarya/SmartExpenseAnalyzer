package com.example.smartexpenseanalyzer.model;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="admin_profiles") public class AdminProfile { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @OneToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="user_id",nullable=false,unique=true) public AppUser appUser; @Column(nullable=false,updatable=false) public Instant createdAt=Instant.now(); @Column(length=160) public String displayTitle="Expense administrator"; public AdminProfile(){} public AdminProfile(AppUser user){this.appUser=user;} }
