package br.com.incode.demodocker.application.dtos;

import br.com.incode.demodocker.infrastructure.persistence.entities.User;

public record UserDTO(
    Long id,
    String name,
    String email) {
        
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
