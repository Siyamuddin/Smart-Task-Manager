package com.taskmanager.taskmanager.Auth;


import com.taskmanager.taskmanager.Config.JwtService;
import com.taskmanager.taskmanager.Entity.Role;
import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Repository.UserRepo;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var user=userRepo.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User NOt found"));
        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
