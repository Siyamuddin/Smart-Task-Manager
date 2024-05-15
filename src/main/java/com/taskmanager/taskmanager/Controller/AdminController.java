package com.taskmanager.taskmanager.Controller;

import com.taskmanager.taskmanager.Payloads.RoleResponse;
import com.taskmanager.taskmanager.Repository.RoleRepo;
import com.taskmanager.taskmanager.Services.RoleServices;
import com.taskmanager.taskmanager.Services.UserServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "JWT-Auth")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private UserServices userServices;
    @PutMapping("role/{roleId}/{userId}")
    public ResponseEntity<RoleResponse> setUserRole(@PathVariable Integer roleId,@PathVariable Integer userId)
    {
        RoleResponse roleResponse=roleServices.setRole(userId,roleId);
        return new ResponseEntity<RoleResponse>(roleResponse, HttpStatus.UNAUTHORIZED);
    }
}
