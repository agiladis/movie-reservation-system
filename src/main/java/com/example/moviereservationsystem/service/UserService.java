package com.example.moviereservationsystem.service;

import com.example.moviereservationsystem.dto.UserRegistrationDTO;
import com.example.moviereservationsystem.model.User;

public interface UserService {

    User registerNewUser(UserRegistrationDTO userDto);
//    Page<User> getAllUser();
//    Optional<User> getUserByEmail(String email);
//    authenticateUser();
//    User updateUser();
//    void deactivateUser();
}
