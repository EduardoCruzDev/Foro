package com.eduardocruzdev.foro.user;


import com.eduardocruzdev.foro.configuration.Routes;
import com.eduardocruzdev.foro.domain.dto.UserRegForm;
import com.eduardocruzdev.foro.domain.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class RegisterController {

    private final UserRegisterService registrationService;
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    public RegisterController(UserRegisterService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/new-user")
    public String userRegistrationForm(Model model, String error) {
        log.info("User registration form");
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("userRegistrationForm", new UserRegForm());
        return Routes.NEW_USER_FORM;
    }

    @PostMapping(value = "/new-user")
    public String registerNewUser(@Valid UserRegForm registrationForm,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return Routes.NEW_USER_FORM;
        }
        User created = registrationService.registerUser(registrationForm);
        model.addAttribute("username", created.getUsername());
        return Routes.REGISTRATION_CONFIRMATION;
    }
}
