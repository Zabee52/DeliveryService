package com.sparta.delivery.controller;

import com.sparta.delivery.dto.UserDto;
import com.sparta.delivery.models.User;
import com.sparta.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public List<User> userList(){
        return userService.userList();
    }

    @PostMapping("/user/login")
    public void login(){
        System.out.println("로그인중..");
    }

    @PostMapping("/user/signup")
    public User singUp(@RequestBody UserDto userDto){
        return userService.signUp(userDto);
    }
}
