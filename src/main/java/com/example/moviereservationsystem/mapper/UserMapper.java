package com.example.moviereservationsystem.mapper;

import com.example.moviereservationsystem.dto.UserDTO;
import com.example.moviereservationsystem.dto.UserRegistrationDTO;
import com.example.moviereservationsystem.model.Role;
import com.example.moviereservationsystem.model.User;

public class UserMapper {

    private UserMapper() { throw new IllegalStateException("Utility class"); }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static User toEntity(UserRegistrationDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());  // Encode password here
        user.setRole(Role.USER);  // Default role or set based on logic

        return user;
    }
}
