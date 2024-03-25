package com.taskmanager.taskmanager.Services;

import com.taskmanager.taskmanager.Payloads.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto,Integer userId);
    TaskDto updateTask(TaskDto taskDto,Integer taskId);
    void deleteTask(Integer taskId);
    TaskDto getTask(Integer taskId);
    List<TaskDto> getAllTask(Integer pageNumber, Integer pageSize, String sortBy,String sortDirect);

}
