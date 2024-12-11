package com.example.ticketing_system_spring_boot.repository;

import com.example.ticketing_system_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

