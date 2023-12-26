package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.exception.AuthenticationException;
import cz.vladarsen.MyFitAPI.exception.RegistrationException;
import cz.vladarsen.MyFitAPI.request.AuthenticationRequest;
import cz.vladarsen.MyFitAPI.request.RegisterRequest;
import cz.vladarsen.MyFitAPI.response.AuthenticationResponse;
import cz.vladarsen.MyFitAPI.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService service;

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String errorMessage;
        log.error("Authentication failed:{}", e.getErrorCode());
        if ("INVALID_CREDENTIALS".equals(e.getErrorCode())) {
            errorMessage = "Invalid username or password";
        } else {
            errorMessage = "Authentication failed: " + e.getMessage();

        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
    }


    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage;
        if ("EMAIL_ALREADY_EXISTS".equals(e.getErrorCode())) {
            errorMessage = "User with this email already exists";
        } else if ("LOGIN_ALREADY_EXISTS".equals(e.getErrorCode())) {
            errorMessage = "User with this login already exists";
        } else {
            errorMessage = "Registration failed: " + e.getMessage();
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(service.register(registerRequest));
        } catch (RegistrationException e) {
            return handleRegistrationException(e);
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(service.authenticate(authenticationRequest));
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }
}