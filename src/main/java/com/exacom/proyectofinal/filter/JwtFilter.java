package com.exacom.proyectofinal.filter;

import com.exacom.proyectofinal.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().matches("/user/v1/api/login|/user/v1/api/create")){
            filterChain.doFilter(request, response);
        } else {
            String userName = null;
            String token = null;
            var headerAuthorization = request.getHeader("Authorization");
            if(headerAuthorization != null){
                token = headerAuthorization.replace(BEARER, "");
                userName = jwtService.extractData(token);
            }
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                var userDetails = userDetailsService.loadUserByUsername(userName);
                if(jwtService.validatedToken(token, userDetails)){
                    var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    new WebAuthenticationDetailsSource().buildDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}
