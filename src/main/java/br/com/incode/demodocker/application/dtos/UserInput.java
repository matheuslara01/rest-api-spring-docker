package br.com.incode.demodocker.application.dtos;

public record UserInput(
	String name,
	String login,
	String email,
	String password) {}
