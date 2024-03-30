package com.taskmanager.taskmanager.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "UDDIN SIYAM",
                        email = "siyamuddin177gmail.com",
                        url = "https://siyamuddin.netlify.app"
                ),
                description = "This is a smart task manager which allows user to create an user account(authenticated by JWT authentication) and create tasks. After creating the task user will get conformation email that the tasks is created and if user forgot to complete the task it will send email to remind the user.",
                title = "Smart Task Manager",
                version = "1.0",
                termsOfService = "Terms of Service"
        ),
        servers ={
                @Server(
                        description = "Local Dev",
                        url = "http://localhost:5000"
                )
        }
)
@SecurityScheme(
        name = "JWT-Auth",
        description = "JWT Authentication Description",
        scheme ="bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in= SecuritySchemeIn.QUERY
)
public class OpenAPiConfiguration {
}
