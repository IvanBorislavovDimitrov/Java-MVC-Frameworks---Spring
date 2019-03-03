package com.ivan.controllers;

import com.ivan.constants.SessionConstants;
import com.ivan.constants.ViewConstants;
import com.ivan.models.binding.UserLoginBindingModel;
import com.ivan.models.service.UserLoginServiceModel;
import com.ivan.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserLoginController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserLoginController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return renderView(ViewConstants.VIEWS_LOGIN, model);
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel, HttpSession httpSession) {
        if (userService.isUserAvailable(modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class))) {
            httpSession.setAttribute(SessionConstants.LOGGED_USER, userLoginBindingModel.getUsername());

            return redirect(ViewConstants.REDIRECT_INDEX);
        }

        return redirect(ViewConstants.REDIRECT_HOME);
    }
}
