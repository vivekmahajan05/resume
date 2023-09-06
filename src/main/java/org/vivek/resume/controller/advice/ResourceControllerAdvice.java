package org.vivek.resume.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.vivek.resume.exception.NotFoundException;
import org.vivek.security.entities.ErrorDetails;

@Slf4j
@ControllerAdvice
public class ResourceControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(NotFoundException nfe, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
        ErrorDetails error = new ErrorDetails("Resource Not Found Exception", requestUri, "Resource does not exists");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
