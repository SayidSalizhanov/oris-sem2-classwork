package com.solncev.service;

import com.solncev.config.MailConfig;
import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;
import com.solncev.entity.Role;
import com.solncev.entity.User;
import com.solncev.repository.RoleRepository;
import com.solncev.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;

    public UserService(UserRepository userRepository,
                      RoleRepository roleRepository,
                      BCryptPasswordEncoder encoder,
                      JavaMailSender mailSender,
                      MailConfig mailConfig) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
    }

    @Transactional
    public UserDto create(CreateUserDto dto, String baseUrl) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        String verification = UUID.randomUUID().toString();
        user.setVerificationCode(verification);

        // Назначаем роль USER по умолчанию
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // send verification code
        sendVerificationEmail(dto, baseUrl, verification);

        return UserDto.fromUser(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    private void sendVerificationEmail(CreateUserDto dto, String baseUrl, String verificationCode) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String content = mailConfig.getContent();

        try {
            mimeMessageHelper.setFrom(mailConfig.getFrom(), mailConfig.getSender());
            mimeMessageHelper.setTo(dto.getEmail());
            mimeMessageHelper.setSubject(mailConfig.getSubject());

            content = content.replace("{name}", dto.getUsername());
            content = content.replace("{url}", baseUrl + "/verification?code=" + verificationCode);

            mimeMessageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid verification code"));
        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);
    }
}

