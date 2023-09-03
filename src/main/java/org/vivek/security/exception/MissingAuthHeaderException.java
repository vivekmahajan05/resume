package org.vivek.security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class MissingAuthHeaderException extends Exception{
    private String errorDesc;
}
