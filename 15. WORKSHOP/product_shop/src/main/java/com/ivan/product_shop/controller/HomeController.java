package com.ivan.product_shop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("view", "views/index");

        return new ModelAndView("base-layout");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(Model model) {
        model.addAttribute("view", "views/home");

        return new ModelAndView("base-layout");
    }
}
