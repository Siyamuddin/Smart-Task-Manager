package com.taskmanager.taskmanager.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAlreadyExistException extends RuntimeException{
    private String user;
    private String email;

    public UserAlreadyExistException(String user, String email) {
        super(String.format("This user and email already exist\nUser: %s\nEmail: %s",user,email));
        this.user = user;
        this.email = email;
    }
}
