package com.taskmanager.taskmanager.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private Set<RoleDto> roles=new HashSet<>();


}
