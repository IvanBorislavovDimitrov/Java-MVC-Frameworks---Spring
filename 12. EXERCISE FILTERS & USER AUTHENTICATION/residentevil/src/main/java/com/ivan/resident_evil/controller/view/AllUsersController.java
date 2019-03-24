package com.ivan.resident_evil.controller.view;

import com.ivan.resident_evil.model.dto.service.UserRegisterServiceModel;
import com.ivan.resident_evil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AllUsersController extends BaseController {

    private final UserService userService;

    @Autowired
    public AllUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public ModelAndView allUsers(Model model) {
        List<UserRegisterServiceModel> users = userService.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            users.removeIf(u -> u.getUsername().equals(currentUserName));
            users.removeIf(u -> u.getRoles().contains("ROLE_ROOT"));
        }

        model.addAttribute("users", users);

        return renderView("views/all-users", model);
    }

    @PostMapping("/make-admin")
    public ModelAndView makeAdmin(@RequestParam("username") String username) {
        userService.addAdminRole(username);
        return redirect("admin/all-users");
    }


    @PostMapping("/make-moderator")
    public ModelAndView makeModerator(@RequestParam("username") String username) {
        userService.addModerator(username);
        return redirect("admin/all-users");
    }

    @PostMapping("/remove-admin")
    public ModelAndView removeAdmin(@RequestParam("username") String username) {
        userService.removeAdmin(username);
        return redirect("admin/all-users");
    }

    @PostMapping("/remove-moderator")
    public ModelAndView removeModerator(@RequestParam("username") String username) {
        userService.removeModerator(username);
        return redirect("admin/all-users");
    }
}
