package com.eduardocruzdev.foro;

import com.eduardocruzdev.foro.configuration.Routes;
import com.eduardocruzdev.foro.domain.dto.UserRegForm;
import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HelloController {
    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/hello", "/welcome"})
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/hello")
    public String loginOrRegister(@ModelAttribute("email") String email,
                                  RedirectAttributes model) {
        User user = userService.findByEmail(email);
        model.addFlashAttribute("email", email);

        if (user == null) {
            model.addFlashAttribute("userRegistrationForm", new UserRegForm());
            return Routes.REDIRECT + Routes.NEW_USER_FORM_PAGE;
        }
        model.addFlashAttribute("username", user.getUsername());
        return Routes.REDIRECT + Routes.LOGIN_PAGE;
    }
}
