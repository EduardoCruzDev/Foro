package com.eduardocruzdev.foro.domain.utiles;

import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UniqueUsernameValidator, String> {
    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByUsername(email);
        return user == null;
    }
}
