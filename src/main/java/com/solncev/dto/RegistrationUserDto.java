package com.solncev.dto;

import lombok.Builder;

@Builder
public record RegistrationUserDto (String name, String password) {
}
