package com.taskmanager.taskmanager.Auth;


import com.taskmanager.taskmanager.Repository.UserRepo;
import lombok.RequiredArgsConstructor;



import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    public AuthenticationResponse register(RegisterRequest request) {
//        var user=User.builder().
        return null;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

}
