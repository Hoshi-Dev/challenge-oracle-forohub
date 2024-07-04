package com.alura.forohub.infra.security;

import com.alura.forohub.domain.user.IUserRepository;
import com.alura.forohub.domain.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            var authHeader = request.getHeader(("Authorization"));
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                var token = authHeader.replace("Bearer ", "");
                var subject = tokenService.getSubject(token);

                if (subject != null) {
                    User user = (User) userRepository.findByName(subject);
                    if (user != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        user.getAuthorities();
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        throw new IllegalArgumentException("Usuario no encontrado: " + subject);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error de autenticación: " + e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }
}
