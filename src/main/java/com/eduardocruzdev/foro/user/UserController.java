package com.eduardocruzdev.foro.user;

import com.eduardocruzdev.foro.domain.model.User;
import com.eduardocruzdev.foro.domain.model.UserProfile;
import com.eduardocruzdev.foro.domain.utiles.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;

    private final UserProfileService userProfileService;

    public UserController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }


    @RequestMapping(value = "/users")
    public String listOfAllUser(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/myprofile")
    public String myProfile(Authentication authentication,
                            Model model) {
        String username = authentication.getName();
        UserProfile userProfile = userProfileService.findOne(username);
        if (userProfile == null) {
            throw new UserNotFoundException();
        }
        model.addAttribute("userProfile", userProfile);
        return "user";
    }

    @RequestMapping(value = "/myprofile/edit/picture", method = RequestMethod.POST)
    public String processAndSaveProfilePicture(@RequestPart MultipartFile profilePicture,
                                               HttpServletRequest request,
                                               Authentication authentication,
                                               RedirectAttributes redirectModel) {
        if (authentication.getName() == null) {
            return "redirect:/login";
        }
        if (profilePicture.isEmpty()) {
            return "redirect:/myprofile";
        }
        User user = userService.findByUsername(authentication.getName());
        try {
            String path =
                    request.getSession().getServletContext().getRealPath("/resources/uploads/avatars/");
            profilePicture.transferTo(new File(path + user.getId() + ".jpg"));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        userService.save(user);
        redirectModel.addFlashAttribute("message", "user.picture.successfully.saved");
        return "redirect:/myprofile";
    }

    @RequestMapping(value = "/logout")
    public String logOutAndRedirectToLoginPage(Authentication authentication,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }
}
