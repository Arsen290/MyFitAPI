package cz.vladarsen.MyFitAPI.rest;

import cz.vladarsen.MyFitAPI.request.AuthenticationRequest;
import cz.vladarsen.MyFitAPI.request.RegisterRequest;
import cz.vladarsen.MyFitAPI.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationRestController( AuthenticationManager authenticationManager)

    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> register(@RequestBody RegisterRequest registerRequest) {

    }

    @PostMapping("/authentication")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

    }

}
