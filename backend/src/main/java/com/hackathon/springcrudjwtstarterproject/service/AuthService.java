package com.hackathon.springcrudjwtstarterproject.service;

import com.hackathon.springcrudjwtstarterproject.dto.request.LoginUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.request.RegisterUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterUserRequest registerUserRequest);
    AuthResponse login(LoginUserRequest loginUserRequest);
}
