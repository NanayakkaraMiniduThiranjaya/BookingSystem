package com.example.ticketing_system_spring_boot.service.jwt;

import com.example.ticketing_system_spring_boot.entity.User;
import com.example.ticketing_system_spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
