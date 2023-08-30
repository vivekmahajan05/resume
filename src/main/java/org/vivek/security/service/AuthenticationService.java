package org.vivek.security.service;

import org.vivek.security.entities.SignUpRequest;
import org.vivek.security.entities.SigninRequest;
import org.vivek.security.entities.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
