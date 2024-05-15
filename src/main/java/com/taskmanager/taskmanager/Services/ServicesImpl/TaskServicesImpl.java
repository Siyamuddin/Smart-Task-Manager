package com.taskmanager.taskmanager.Services.ServicesImpl;

import com.taskmanager.taskmanager.Entity.Task;
import com.taskmanager.taskmanager.Entity.User;
import com.taskmanager.taskmanager.Exceptions.ResourceNotFoundException;
import com.taskmanager.taskmanager.Payloads.TaskDto;
import com.taskmanager.taskmanager.Repository.TaskRepo;
import com.taskmanager.taskmanager.Repository.UserRepo;
import com.taskmanager.taskmanager.Services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@EnableScheduling
public class TaskServicesImpl implements TaskService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSenderService mailSenderService;
    @Override
    public TaskDto createTask(TaskDto taskDto, Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id",userId));
        Task task=modelMapper.map(taskDto,Task.class);
        task.setUser(user);
        Task createdTask=taskRepo.save(task);
        String userEmail=user.getEmail();
        String taskTittle=taskDto.getTaskTitle();
        String taskDescription=taskDto.getTaskDescription();
        mailSenderService.sendEmail(userEmail,taskTittle,taskDescription);

        return modelMapper.map(createdTask,TaskDto.class);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Integer taskId) {
    Task task=taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",taskId));
    task.setTaskTitle(taskDto.getTaskTitle());
    task.setTaskDescription(taskDto.getTaskDescription());
    task.setDeadline(taskDto.getDeadline());
    task.setDone(taskDto.isDone());
    Task updatedTask=taskRepo.save(task);
        return modelMapper.map(updatedTask,TaskDto.class);
    }

    @Override
    public void deleteTask(Integer taskId) {
        Task task=taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",taskId));
        taskRepo.delete(task);
    }

    @Override
    public TaskDto getTask(Integer taskId) {
        Task task=taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("User","user Id",taskId));
        return modelMapper.map(task,TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTask(Integer pageNumber, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=null;
        if(sortDirect.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else
        {
            sort=Sort.by(sortBy).descending();
        }
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Task> tasks=taskRepo.findAll(pageable);
        List<TaskDto> taskDtoList=tasks.stream().map((task)-> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());

        return taskDtoList;
    }
}
