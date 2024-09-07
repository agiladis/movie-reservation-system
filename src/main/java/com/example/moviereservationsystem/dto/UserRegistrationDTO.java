package com.example.moviereservationsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "username is required")
    private String username;

    @Email(message = "email should be valid")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 4, message = "password should have at least 4 characters")
    private String password;
}
