package com.taskmanager.taskmanager.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "task")


public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;
    private String taskTitle;
    private String taskDescription;
    private Date deadline;
    private boolean isDone;
    @CreationTimestamp
    private Date timeWhenCreated;
    @UpdateTimestamp
    private Date timeWheUpdated;
    @ManyToOne
    User user;

    public Task() {
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public Date getTimeWhenCreated() {
        return timeWhenCreated;
    }

    public void setTimeWhenCreated(Date timeWhenCreated) {
        this.timeWhenCreated = timeWhenCreated;
    }

    public Date getTimeWheUpdated() {
        return timeWheUpdated;
    }

    public void setTimeWheUpdated(Date timeWheUpdated) {
        this.timeWheUpdated = timeWheUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
