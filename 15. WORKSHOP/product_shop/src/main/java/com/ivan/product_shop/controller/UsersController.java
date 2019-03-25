package com.ivan.product_shop.controller;

import com.ivan.product_shop.model.binding.UserBindingModel;
import com.ivan.product_shop.model.binding.UserViewModel;
import com.ivan.product_shop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        model.addAttribute("view", "views/login");

        return new ModelAndView("base-layout");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        model.addAttribute("view", "views/register");

        return new ModelAndView("base-layout");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute("user") UserBindingModel userBindingModel) {

        userService.save(userBindingModel);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, Model model) {
        model.addAttribute("view", "views/profile");

        UserViewModel user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return new ModelAndView("base-layout");
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileView(Principal principal, Model model) {
        model.addAttribute("view", "views/edit-profile");

        UserViewModel user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return new ModelAndView("base-layout");
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute("user") UserViewModel userViewModel) {
        userService.update(userViewModel);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView allUsers(Model model) {
        model.addAttribute("view", "views/all-users");

        List<UserViewModel> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return new ModelAndView("base-layout");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setAdmin(@PathVariable("id") String id) {

        userService.addAdmin(id);

        return new ModelAndView("redirect:/users/all");
    }


    @PostMapping("/set-moderator/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setModerator(@PathVariable("id") String id) {

        userService.addModerator(id);

        return new ModelAndView("redirect:/users/all");
    }


    @PostMapping("/set-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView setUser(@PathVariable("id") String id) {

        userService.addUser(id);

        return new ModelAndView("redirect:/users/all");
    }
}
