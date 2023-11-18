package com.exacom.proyectofinal.service;

import com.exacom.proyectofinal.domain.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDTO user);

    boolean validatedToken(String token, UserDetails user);

    public String extractData(String token);
}
