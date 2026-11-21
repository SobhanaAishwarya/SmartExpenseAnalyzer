package com.example.smartexpenseanalyzer.repository;
import com.example.smartexpenseanalyzer.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*; public interface TripRepository extends JpaRepository<Trip,Long>{ List<Trip> findByUserOrderByStartDateDesc(AppUser user); Optional<Trip> findByIdAndUser(Long id,AppUser user); }
