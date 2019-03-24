package com.ivan.resident_evil.controller.view;

import com.ivan.resident_evil.constants.Constants;
import com.ivan.resident_evil.model.dto.binding.UserRegisterBindingModel;
import com.ivan.resident_evil.model.dto.service.UserRegisterServiceModel;
import com.ivan.resident_evil.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/users")
public class UserRegisterController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {


        return renderView(Constants.USER_REGISTER, model);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return renderView(Constants.USER_REGISTER, model);
        }
        try {
            userService.save(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));
        } catch (Exception e) {
            return redirect(Constants.REDIRECT_INDEX);
        }

        return redirect(Constants.REDIRECT_INDEX);
    }

    @ModelAttribute("user")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}
