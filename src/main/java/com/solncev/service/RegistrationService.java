package com.solncev.service;

import com.solncev.dto.RegistrationUserDto;
import com.solncev.entity.User;
import com.solncev.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public RegistrationService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public Long saveUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.name());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.password()));

        return userRepository.save(user).getId();
    }
}
