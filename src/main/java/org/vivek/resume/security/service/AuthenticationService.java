package org.vivek.resume.security.service;

import org.vivek.resume.security.model.AuthenticationResponse;
import org.vivek.resume.security.model.SignUpRequest;
import org.vivek.resume.security.model.SigninRequest;

public interface AuthenticationService {

    AuthenticationResponse signup(SignUpRequest request);

    AuthenticationResponse signin(SigninRequest request);
}
