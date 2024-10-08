package com.example.moviereservationsystem.controller;

import com.example.moviereservationsystem.dto.UserDTO;
import com.example.moviereservationsystem.dto.UserLoginDTO;
import com.example.moviereservationsystem.dto.UserRegistrationDTO;
import com.example.moviereservationsystem.exception.UserErrorException;
import com.example.moviereservationsystem.mapper.UserMapper;
import com.example.moviereservationsystem.model.User;
import com.example.moviereservationsystem.service.UserService;
import com.example.moviereservationsystem.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDto) {

        try {
            User newUser = userService.registerNewUser(userRegistrationDto);
            UserDTO userDTO = UserMapper.toDTO(newUser);

            ApiResponse<UserDTO> response = new ApiResponse<>("success", "user registered successfully", userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserErrorException e) {
            ApiResponse<UserDTO> response = new ApiResponse<>("error", e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        try {
            UserDTO userDTO = userService.login(userLoginDTO);

            ApiResponse<UserDTO> response = new ApiResponse<>("success", "login success", userDTO);
            return ResponseEntity.ok().body(response);
        } catch (UserErrorException e) {
            ApiResponse<UserDTO> response = new ApiResponse<>("error", e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
