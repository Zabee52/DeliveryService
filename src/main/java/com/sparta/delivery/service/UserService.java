package com.sparta.delivery.service;

import com.sparta.delivery.dto.UserDto;
import com.sparta.delivery.models.User;
import com.sparta.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(UserDto userDto) {
        return userRepository.save(
                new User(
                    userDto.getUserId(),
                    passwordEncoder.encode(userDto.getPassword()),
                    userDto.getUsername(),
                    userDto.getRole()
                )
        );
    }

    public List<User> userList() {
        return userRepository.findAll();
    }
}
