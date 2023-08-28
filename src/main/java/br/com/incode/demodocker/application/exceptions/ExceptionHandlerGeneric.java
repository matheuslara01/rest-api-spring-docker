package br.com.incode.demodocker.application.exceptions;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.incode.demodocker.application.dtos.ResponseDTO;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.NoResultException;

@ControllerAdvice
public class ExceptionHandlerGeneric extends ResponseEntityExceptionHandler {

    @Autowired
    private Logger logger;

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> handleNoResultExceptionException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ResponseDTO(false, ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityPersistenceException.class)
    protected ResponseEntity<?> handleEntityPersistenceException(EntityPersistenceException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseDTO(false, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityDeleteException.class)
    protected ResponseEntity<?> handleEntityDeleteException(EntityDeleteException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseDTO(false, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseDTO(false, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException ex) {
        return new ResponseEntity<>(new ResponseDTO(false, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new ResponseDTO(false, "Email or password invalid!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(
                new ResponseDTO(false, "Internal server error. Contact your system administrator."),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
