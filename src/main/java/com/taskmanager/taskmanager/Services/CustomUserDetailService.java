package com.taskmanager.taskmanager.Services;

import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Exceptions.ResourceNotFoundException;
import com.taskmanager.taskmanager.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not found with this email."));
        return user;
    }

    public String deleteUserById(Integer userId) throws UsernameNotFoundException {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user ID",userId));
        userRepo.deleteById(userId);
        return "User is deleted successfully";
    }
}