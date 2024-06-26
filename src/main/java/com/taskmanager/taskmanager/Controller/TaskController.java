package com.taskmanager.taskmanager.Controller;

import com.taskmanager.taskmanager.Payloads.ApiResponse;
import com.taskmanager.taskmanager.Payloads.TaskDto;
import com.taskmanager.taskmanager.Services.TaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@SecurityRequirement(name = "JWT-Auth")
@Tag(name = "Tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/create/{userId}")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @PathVariable Integer userId)
    {
        TaskDto taskDto1=taskService.createTask(taskDto,userId);
        return new ResponseEntity<TaskDto>(taskDto1, HttpStatus.CREATED);
    }
    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable Integer taskId)
    {
        TaskDto taskDto1=taskService.updateTask(taskDto,taskId);
        return new ResponseEntity<TaskDto>(taskDto1, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{taskId}")
    public ApiResponse deleteTask(@Valid @PathVariable Integer taskId)
    {
        taskService.deleteTask(taskId);
        return new ApiResponse("The task is deleted successfully",true);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@Valid @PathVariable Integer taskId)
    {
        TaskDto taskDto=taskService.getTask(taskId);
        return new ResponseEntity<TaskDto>(taskDto,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTask(@Valid @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = "taskId",required = false) String sortBy,
                                                    @RequestParam(value = "sortDirect", defaultValue = "asc",required = false) String sortDirect){
        List<TaskDto> taskDtoList=taskService.getAllTask(pageNumber,pageSize,sortBy,sortDirect);
        return new ResponseEntity<List<TaskDto>>(taskDtoList,HttpStatus.OK);
    }

}
