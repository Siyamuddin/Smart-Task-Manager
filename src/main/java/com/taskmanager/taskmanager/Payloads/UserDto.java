package com.taskmanager.taskmanager.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email(message = "Please enter a valid Email.")
    private String email;
    @NotEmpty
    @Size(min = 8,max = 16,message = "The password must contain minimum 8 characters and maximum 16 characters")
    private String password;
    private Set<RoleDto> roles=new HashSet<>();


}
