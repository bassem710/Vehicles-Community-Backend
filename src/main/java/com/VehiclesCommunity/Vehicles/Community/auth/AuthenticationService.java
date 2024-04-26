package com.VehiclesCommunity.Vehicles.Community.auth;

import com.VehiclesCommunity.Vehicles.Community.config.JwtService;
import com.VehiclesCommunity.Vehicles.Community.user.Role;
import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, Object> register(RegisterRequest request) {
        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        userRepository.save(user);
        return AuthenticationResponse(user);
    }

    public Map<String, Object> login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();
        return AuthenticationResponse(user);
    }

    private Map<String, Object> AuthenticationResponse(User user) {
        var jwtToken = jwtService.generateToken(user);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("firstName", user.getFirstName());
        responseData.put("lastName", user.getLastName());
        responseData.put("role", user.getRole());
        responseData.put("id", user.getId());
        responseData.put("email", user.getUsername());
        Map<String, Object> response = new HashMap<>();
        response.put("userData", responseData);
        response.put("token", jwtToken);
        return response;
    }
}
