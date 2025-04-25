package com.solncev.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CreateUserDto {

    private String username;

    private String password;

    private String email;

}
