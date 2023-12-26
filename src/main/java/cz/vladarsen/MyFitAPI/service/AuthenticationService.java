package cz.vladarsen.MyFitAPI.service;

import cz.vladarsen.MyFitAPI.config.JwtService;
import cz.vladarsen.MyFitAPI.entity.Role;
import cz.vladarsen.MyFitAPI.entity.RoleName;
import cz.vladarsen.MyFitAPI.entity.User;
import cz.vladarsen.MyFitAPI.exception.AuthenticationException;
import cz.vladarsen.MyFitAPI.exception.RegistrationException;
import cz.vladarsen.MyFitAPI.repository.RoleRepository;
import cz.vladarsen.MyFitAPI.repository.UserRepository;
import cz.vladarsen.MyFitAPI.request.AuthenticationRequest;
import cz.vladarsen.MyFitAPI.request.RegisterRequest;
import cz.vladarsen.MyFitAPI.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //Get role user
    private List<Role> getRoleUser() {
        Role userRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return Collections.singletonList(userRole);
    }

    //Register user
    public AuthenticationResponse register(RegisterRequest request) {
        List<Role> roleUser = getRoleUser();

        // Check user with this email
        if (repository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("User with this email already exists", "EMAIL_ALREADY_EXISTS");
        }

        // Check user with this login
        if (repository.existsByUsername(request.getUsername())) {
            throw new RegistrationException("User with this login already exists", "LOGIN_ALREADY_EXISTS");
        }
        // Build object user for register
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(roleUser)
                .build();
        // Add date when add entity in db
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        user.setCreated(currentDate);
        user.setUpdated(currentDate);
        // Save user in db
        repository.save(user);
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();

    }

    //Authenticate user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Check user

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));


        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var JwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();

    }
}
