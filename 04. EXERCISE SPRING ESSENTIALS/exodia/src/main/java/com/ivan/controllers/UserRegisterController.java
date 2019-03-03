package com.ivan.controllers;

import com.ivan.constants.ViewConstants;
import com.ivan.exceptions.InvalidRegisterUserBindingModelException;
import com.ivan.models.binding.UserRegisterBindingModel;
import com.ivan.models.service.UserRegisterServiceModel;
import com.ivan.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserRegisterController extends BaseController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserRegisterController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        return renderView(ViewConstants.VIEWS_REGISTER, model);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute @Valid UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return redirect(ViewConstants.REDIRECT_REGISTER);
        }

        try {
            userService.save(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));
        } catch (InvalidRegisterUserBindingModelException e) {
            return redirect(ViewConstants.REDIRECT_REGISTER);
        }

        return redirect(ViewConstants.REDIRECT_LOGIN);
    }
}
