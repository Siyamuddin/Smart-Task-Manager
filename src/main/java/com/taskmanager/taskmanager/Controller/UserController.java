package com.taskmanager.taskmanager.Controller;

import com.taskmanager.taskmanager.Payloads.ApiResponse;
import com.taskmanager.taskmanager.Payloads.TaskDto;
import com.taskmanager.taskmanager.Payloads.UserDto;
import com.taskmanager.taskmanager.Services.UserServices;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createdUserDto=this.userServices.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId)
    {
        UserDto createdUserDto=this.userServices.updateUser(userDto,userId);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
    {
        userServices.deleteUser(userId);
        ApiResponse apiResponse=new ApiResponse("User deleted successfully",true);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId)
    {
        UserDto userDto=userServices.getUser(userId);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                     @RequestParam(value = "sortDirect",defaultValue = "asc",required = false) String sortDirect)
    {
        List<UserDto> userDtos=userServices.getAllUsers(pageNumber,pageSize,sortBy,sortDirect);
        return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword)
    {
        List<UserDto> userDtos=userServices.searchUser(keyword);
        return new ResponseEntity<>(userDtos,HttpStatus.FOUND);
    }
    @GetMapping("/isDone/{taskId}")
    public ResponseEntity<Boolean> TaskIsDone(@PathVariable Integer taskId)
    {
        Boolean isDone=userServices.isComplete(taskId);
        return  new ResponseEntity<Boolean>(isDone,HttpStatus.OK);
    }
    @GetMapping("/task/{userId}")
    public ResponseEntity<List<TaskDto>> getAllTaskByUser(@PathVariable Integer userId)
    {
        List<TaskDto> taskDtoList=userServices.getTaskByUser(userId);
        return new ResponseEntity<List<TaskDto>>(taskDtoList,HttpStatus.OK);
    }
    @PutMapping("/complete/{taskId}/{taskStatus}")
    public String completeTask(@PathVariable Integer taskId,@PathVariable boolean taskStatus)
    {
        userServices.completeTask(taskId,taskStatus);
        if(taskStatus==true)
        {
            return "The task is completed!";
        }
        return "The task is not completed yet!";
    }

}
