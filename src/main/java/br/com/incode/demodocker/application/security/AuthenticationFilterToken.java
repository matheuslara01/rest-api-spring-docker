package br.com.incode.demodocker.application.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.incode.demodocker.application.dtos.ResponseDTO;
import br.com.incode.demodocker.application.exceptions.InvalidTokenException;
import br.com.incode.demodocker.domain.services.TokenService;
import br.com.incode.demodocker.domain.services.UserService;
import br.com.incode.demodocker.infrastructure.persistence.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilterToken extends OncePerRequestFilter {
    
    private TokenService tokenService;
    private UserService userService;

    public AuthenticationFilterToken(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = getTokenRequest(request);

            boolean isValid = tokenService.isTokenValid(token);

            if (isValid) {
                authenticateUser(token);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(new ObjectMapper().writeValueAsString(new ResponseDTO(false, e.getMessage())));
        }
    }

    private String getTokenRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            String requestURI = request.getRequestURI();

            if (requestURI.contains("auth") || requestURI.contains("swagger") || requestURI.contains("api-docs") || requestURI.contains("actuator")) {
                return null;
            } else {
                throw new InvalidTokenException("Token is not present in the request header!");
            }
        }

        return token.substring(7);
    }

    private void authenticateUser(String token) {
        Long id = tokenService.getIdUser(token);
        User user = userService.findById(id);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
