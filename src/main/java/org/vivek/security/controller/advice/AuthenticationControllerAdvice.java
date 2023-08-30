package org.vivek.security.controller.advice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.vivek.security.entities.ErrorDetails;
import org.vivek.security.exception.MissingAuthHeaderException;
import org.vivek.security.exception.UserServiceException;

@ControllerAdvice
@Slf4j
public class AuthenticationControllerAdvice {

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorDetails> badCredentials(BadCredentialsException be, WebRequest request) {
        log.info("AuthenticationControllerAdvice::badCredentials");
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error = new ErrorDetails("BadCredentials Exception", requestUri, be.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<ErrorDetails> signatureException(SignatureException se, WebRequest request) {
        log.info("AuthenticationControllerAdvice::signatureException");
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error = new ErrorDetails("Signature Exception", requestUri, se.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error =new ErrorDetails("Authentication Exception", requestUri, ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), ex.getErrorStatusCode());
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error =new ErrorDetails("ExpiredJwtException", requestUri, ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {MissingAuthHeaderException.class})
    public ResponseEntity<Object> handleMissingAuthHeaderException(MissingAuthHeaderException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error =new ErrorDetails("Authorization Header Exception", requestUri, ex.getErrorDesc());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * handlerOtherExceptions handles any unhandled exceptions.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error =new ErrorDetails("Exception", requestUri, ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
