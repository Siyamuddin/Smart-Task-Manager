package com.taskmanager.taskmanager.Services.ServicesImpl;

import com.taskmanager.taskmanager.Entity.Role;
import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Exceptions.ResourceNotFoundException;
import com.taskmanager.taskmanager.Payloads.RoleResponse;
import com.taskmanager.taskmanager.Repository.RoleRepo;
import com.taskmanager.taskmanager.Repository.UserRepo;
import com.taskmanager.taskmanager.Services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public RoleResponse setRole(Integer userId, Integer roleId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user ID",userId));
        List<Role> roles=roleRepo.findAll();
        user.getRoles().add(roles.get(roleId));
        userRepo.save(user);
        RoleResponse roleResponse=new RoleResponse();
        roleResponse.setUserName(user.getFirstName());
        roleResponse.setUserId(userId);
        roleResponse.setUserRole(roles.get(roleId).getName());
        return roleResponse;
    }
}
