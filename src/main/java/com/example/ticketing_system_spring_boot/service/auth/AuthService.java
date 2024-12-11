package com.example.ticketing_system_spring_boot.service.auth;


import com.example.ticketing_system_spring_boot.dto.AuthenticationRequest;
import com.example.ticketing_system_spring_boot.dto.AuthenticationResponse;
import com.example.ticketing_system_spring_boot.dto.SignupRequest;

public interface AuthService {
    void register(SignupRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}



