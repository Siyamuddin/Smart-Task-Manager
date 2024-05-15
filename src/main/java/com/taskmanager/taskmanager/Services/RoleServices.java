package com.taskmanager.taskmanager.Services;

import com.taskmanager.taskmanager.Entity.Role;
import com.taskmanager.taskmanager.Payloads.RoleResponse;

public interface RoleServices {
    RoleResponse setRole(Integer userId, Integer roleId);
}
