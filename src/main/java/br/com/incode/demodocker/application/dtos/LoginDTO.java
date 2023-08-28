package br.com.incode.demodocker.application.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginDTO(
    String login,
    String password) {
    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
