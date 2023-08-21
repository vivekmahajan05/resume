package org.vivek.resume.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vivek.resume.security.model.AuthenticationRequest;
import org.vivek.resume.security.model.AuthenticationResponse;
import org.vivek.resume.security.model.SignUpRequest;
import org.vivek.resume.security.model.SigninRequest;
import org.vivek.resume.security.service.AuthenticationService;
import org.vivek.resume.security.service.UserService;
import org.vivek.resume.security.util.JwtUtil;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/resumeApi/v1/")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping(path = "authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthenticationResponse> getAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        log.info("inside getAuthentication!");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException be){
            throw new Exception(" Invalid Username or password!",be);
        }
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getUserName());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
