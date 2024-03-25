package com.taskmanager.taskmanager.Repository;

import com.taskmanager.taskmanager.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    List<User> findByUserNameContaining(String userName);
}
