package com.solncev.dto;

import com.solncev.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String username;

    private String email;

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
