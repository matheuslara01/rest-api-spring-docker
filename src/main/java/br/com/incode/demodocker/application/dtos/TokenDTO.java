package br.com.incode.demodocker.application.dtos;

public record TokenDTO(
    String token, 
    String type, 
    Long expiration
) {}
