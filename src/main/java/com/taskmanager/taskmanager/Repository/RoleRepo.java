package com.taskmanager.taskmanager.Repository;

import com.taskmanager.taskmanager.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
