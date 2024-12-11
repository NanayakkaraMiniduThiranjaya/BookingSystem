package com.example.ticketing_system_spring_boot.service.auth;

import com.example.ticketing_system_spring_boot.dto.AuthenticationRequest;
import com.example.ticketing_system_spring_boot.dto.AuthenticationResponse;
import com.example.ticketing_system_spring_boot.dto.SignupRequest;
import com.example.ticketing_system_spring_boot.entity.User;
import com.example.ticketing_system_spring_boot.enums.UserRole;
import com.example.ticketing_system_spring_boot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ticketing_system_spring_boot.util.JwtUtil;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));
        userRepository.save(user);
    }


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthenticationResponse(token);
    }
}


