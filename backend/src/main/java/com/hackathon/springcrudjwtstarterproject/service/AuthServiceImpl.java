package com.hackathon.springcrudjwtstarterproject.service;

import com.hackathon.springcrudjwtstarterproject.dto.request.LoginUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.request.RegisterUserRequest;
import com.hackathon.springcrudjwtstarterproject.dto.response.AuthResponse;
import com.hackathon.springcrudjwtstarterproject.dto.response.UserResponse;
import com.hackathon.springcrudjwtstarterproject.entity.User;
import com.hackathon.springcrudjwtstarterproject.repository.UserRepository;
import com.hackathon.springcrudjwtstarterproject.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    @Transactional
    public AuthResponse register(RegisterUserRequest request) {
        UserResponse userResponse = userService.createUser(request);

        String token = jwtService.generateToken(userResponse.getEmail());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByEmail(loginUserRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
