package com.taskmanager.taskmanager.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;


}
