package com.finalexample.demo.model.request;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Data
public class RegisterUserRequest {

    @NotBlank(message = "Username cannot be empty.")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;


    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Invalid email format.")
    private String email;




}
