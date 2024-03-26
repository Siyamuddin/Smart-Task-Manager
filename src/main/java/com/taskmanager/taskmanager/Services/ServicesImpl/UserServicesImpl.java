package com.taskmanager.taskmanager.Services.ServicesImpl;

import com.taskmanager.taskmanager.Entity.Task;
import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Exceptions.ResourceNotFoundException;
import com.taskmanager.taskmanager.Payloads.TaskDto;
import com.taskmanager.taskmanager.Payloads.UserDto;
import com.taskmanager.taskmanager.Repository.TaskRepo;
import com.taskmanager.taskmanager.Repository.UserRepo;
import com.taskmanager.taskmanager.Services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepo taskRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        User createdUser=this.userRepo.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",userId));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser=userRepo.save(user);

        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));
        userRepo.deleteById(userId);
    }

    @Override
    public UserDto getUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user Id",userId));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=null;
        if(sortDirect.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else
        {
            sort=Sort.by(sortBy).descending();
        }
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<User> users=userRepo.findAll(pageable);
        List<UserDto> userDtos=users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> users=userRepo.findByFirstNameContaining(keyword);
        List<UserDto> userDtos=users.stream().map((user)-> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean isComplete(Integer taskId) {
        Task task=taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task","task Id",taskId));
        boolean isDone=task.isDone();
        return isDone;
    }

    @Override
    public String completeTask(Integer taskId, boolean taskStatus) {
        Task task=taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task","task Id",taskId));
        task.setDone(taskStatus);
        taskRepo.save(task);

        return "The task has been completed.";
    }

    @Override
    public List<TaskDto> getTaskByUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",userId));
        List<Task> tasks=taskRepo.findByUser(user);
        List<TaskDto> taskDtoList=tasks.stream().map((task)-> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());

        return taskDtoList;
    }
}
