package com.example.moviereservationsystem.service;

import com.example.moviereservationsystem.dto.UserDTO;
import com.example.moviereservationsystem.dto.UserLoginDTO;
import com.example.moviereservationsystem.dto.UserRegistrationDTO;
import com.example.moviereservationsystem.exception.UserErrorException;
import com.example.moviereservationsystem.mapper.UserMapper;
import com.example.moviereservationsystem.model.User;
import com.example.moviereservationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new UserErrorException("user with this email already exist");
        }

        User newUser = UserMapper.toEntity(userDto);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public UserDTO login(UserLoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserErrorException("email not found"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new UserErrorException("invalid email or password");
        }

        UserDTO userDTO = UserMapper.toDTO(user);

        return userDTO;
    }
}
