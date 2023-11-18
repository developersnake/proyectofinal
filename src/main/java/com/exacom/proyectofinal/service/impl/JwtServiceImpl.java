package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.UserDTO;
import com.exacom.proyectofinal.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.function.Function;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    public static final String JWT_SECRET = "a6875c94c6bc40f7635cc9ebea1fd97df5d75b71212b99bba7846fa64a9f87f1";
    @Override
    public String generateToken(UserDTO user) {
        var payload = new HashMap<String, Object>();
        payload.put("roles", user.getRoles());
        payload.put("uid", user.getId());
        payload.put("email", user.getEmail());
        return createToken(payload, user.getUsername());
    }

    @Override
    public boolean validatedToken(String token, UserDetails user) {
        log.info("token: " + token);
        log.info("userDetailsService: " + user);
        var userData = extractData(token);
        return (userData.equalsIgnoreCase(user.getUsername()) && !isExpiredToken(token));
    }

    @Override
    public String extractData(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private String createToken(HashMap<String, Object> payload, String subject) {
        return Jwts.builder().claims().empty().add(payload).and().subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)) )
                .compact();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final var claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .build().parseSignedClaims(token).getPayload();
    }

    private boolean isExpiredToken(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return this.extractClaims(token, Claims::getExpiration);
    }
}
