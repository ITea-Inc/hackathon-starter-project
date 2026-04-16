package com.hackathon.springcrudjwtstarterproject.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String email);

    String extractEmail(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
