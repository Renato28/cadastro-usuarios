package br.com.firstdecision.user.registration.api.service.exception.exceptionHandlers;

import br.com.firstdecision.user.registration.api.service.exception.StandardError;
import br.com.firstdecision.user.registration.api.service.exception.UsuarioNotFoundException;
import br.com.firstdecision.user.registration.api.service.exception.UsuarioRegistradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<StandardError> usuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioRegistradoException.class)
    public ResponseEntity<StandardError> usuarioRegistradoException(UsuarioRegistradoException ex, HttpServletRequest request) {

        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
