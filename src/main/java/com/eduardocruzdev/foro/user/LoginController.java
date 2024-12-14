package com.eduardocruzdev.foro.user;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            log.info("Logging failed: {}", error);
            model.addAttribute("error", error);
        }

        if (logout != null) {
            model.addAttribute("message", "login.logout");
        }

        return "login";
    }
}
