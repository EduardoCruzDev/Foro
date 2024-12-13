package com.eduardocruzdev.foro.user;

import com.eduardocruzdev.foro.domain.dto.UserRegForm;
import com.eduardocruzdev.foro.domain.model.MessageEmail;
import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.user.email.EmailMessageService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service


public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailMessageService emailMessageService;
    private static final Logger log = LoggerFactory.getLogger(UserRegisterService.class);


    public UserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailMessageService emailMessageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailMessageService = emailMessageService;
    }

    public User registerUser(UserRegForm form) {
        log.info("Register new user {}, {}", form.getEmail(), form.getUsername());
        String confirmationCode = scheduleConfirmationMessage(form.getEmail());
        User newUser = buildUser(form, confirmationCode);
        return userRepository.save(newUser);
    }

    private String scheduleConfirmationMessage(String email) {
        String randomString = Long.toHexString(Double.doubleToLongBits(Math.random()));
        MessageEmail confirmationMessage = MessageEmail.builder()
                .recipient(email)
                .content(randomString)
                .type(MessageEmail.EmailMessageType.CONFIRMATION)
                .build();
        emailMessageService.scheduleMessage(confirmationMessage);
        return randomString;
    }

    private User buildUser(UserRegForm form, String confirmationCode) {
        return User.builder()
                .email(form.getEmail())
                .emailToken(confirmationCode)
                .username(form.getUsername())
                .enabled(false)
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
    }


}
