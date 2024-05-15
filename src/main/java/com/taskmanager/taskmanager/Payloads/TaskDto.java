package com.taskmanager.taskmanager.Payloads;

import com.taskmanager.taskmanager.Entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

public class TaskDto {
    @NotEmpty(message = "Title must not be empty.")
    private String taskTitle;
    @NotEmpty(message = "Title must not be empty.")
    private String taskDescription;
    private Date deadline;
    @NotEmpty(message = "Title must not be empty.")
    private boolean isDone;

    public TaskDto() {
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
