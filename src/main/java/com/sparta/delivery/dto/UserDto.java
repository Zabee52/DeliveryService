package com.sparta.delivery.dto;

import com.sparta.delivery.models.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserDto {
    private String userId;
    private String password;
    private String username;
    private UserRoleEnum role;
}
