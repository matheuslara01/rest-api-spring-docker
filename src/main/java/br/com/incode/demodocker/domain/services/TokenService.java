package br.com.incode.demodocker.domain.services;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.incode.demodocker.application.dtos.TokenDTO;
import br.com.incode.demodocker.application.exceptions.InvalidTokenException;
import br.com.incode.demodocker.infrastructure.persistence.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

    @Autowired
    private Logger logger;

    @Value("${demodocker.jwt.expiration}")
    private String expiration;

    @Value("${demodocker.jwt.secret}")
    private String secret;

    public TokenDTO generateToken(Authentication authentication) {

        User logged = (User) authentication.getPrincipal();
        Long longExpiration = Long.parseLong(expiration);
        Date today = new Date();
        Date dateExpiration = new Date(today.getTime() + longExpiration);

        String token = Jwts.builder()
                .setIssuer("demodocker")
                .setSubject(logged.getId().toString())
                .claim("id", logged.getId())
                .claim("name", logged.getName())
                .claim("email", logged.getEmail())
                .setIssuedAt(today).setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS256, secret).compact();

        return new TokenDTO(token, "Bearer", longExpiration);
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Malformed Token!");
            throw new InvalidTokenException("Malformed Token!");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported Token!");
            throw new InvalidTokenException("Unsupported Token!");
        } catch (ExpiredJwtException e) {
            logger.error("Expired Token!");
            throw new InvalidTokenException("Expired Token!");
        } catch (IllegalArgumentException e) {
            logger.error("Illegal Argument Token!");
            return false;
        }

    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
