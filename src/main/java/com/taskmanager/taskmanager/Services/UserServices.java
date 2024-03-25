package com.taskmanager.taskmanager.Services;

import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Payloads.TaskDto;
import com.taskmanager.taskmanager.Payloads.UserDto;

import java.util.List;

public interface UserServices {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    void deleteUser(Integer userId);
    UserDto getUser(Integer userId);
    List<UserDto> getAllUsers(Integer pageNumber,Integer pageSize,String sortBy,String sortDirect);
    List<UserDto> searchUser(String name);
    boolean isComplete(Integer taskId);
    String completeTask(Integer taskId,boolean taskStatus);
    List<TaskDto> getTaskByUser(Integer userId);
}
