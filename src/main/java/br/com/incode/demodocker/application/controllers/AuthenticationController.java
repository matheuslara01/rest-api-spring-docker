package br.com.incode.demodocker.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.incode.demodocker.application.dtos.LoginDTO;
import br.com.incode.demodocker.application.dtos.ResponseDTO;
import br.com.incode.demodocker.application.dtos.TokenDTO;
import br.com.incode.demodocker.domain.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
	@Operation(summary = "Login of Users in the API")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success in the request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Email or password is invalid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "401", description = "Not Authorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Server internal error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class)) }),
			@ApiResponse(responseCode = "504", description = "Timeout", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class)) }), })
	public TokenDTO authentication(@RequestBody LoginDTO loginDTO) {
		return authenticationService.login(loginDTO);
	}

	
}
