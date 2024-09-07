package com.example.moviereservationsystem.service;

import com.example.moviereservationsystem.dto.UserRegistrationDTO;
import com.example.moviereservationsystem.exception.UserAlreadyExistsException;
import com.example.moviereservationsystem.mapper.UserMapper;
import com.example.moviereservationsystem.model.User;
import com.example.moviereservationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(UserRegistrationDTO userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException("user with this email already exist");
        }

        User newUser = UserMapper.toEntity(userDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }
}
