package org.vivek.security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class UserServiceException extends Exception{
    private String errorDesc;
    private HttpStatus errorStatusCode;
}
