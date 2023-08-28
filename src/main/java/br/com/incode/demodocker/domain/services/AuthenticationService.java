package br.com.incode.demodocker.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.incode.demodocker.application.dtos.LoginDTO;
import br.com.incode.demodocker.application.dtos.TokenDTO;
import br.com.incode.demodocker.infrastructure.persistence.entities.User;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> user = userService.findByLogin(login);

        if (user.isPresent()) {
            return (UserDetails) user.get();
        }

        throw new UsernameNotFoundException("Invalid Login!");
    }

    public TokenDTO login(LoginDTO form) {
        try {
            return tokenService.generateToken(authenticationManager.authenticate(form.converter()));
        } catch (AuthenticationException authenticationException) {
            throw authenticationException;
        }
    }
}
