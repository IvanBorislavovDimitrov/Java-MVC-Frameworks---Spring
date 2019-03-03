package com.ivan.controllers;

import com.ivan.constants.ViewConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView home(Model model) {
        return renderView(ViewConstants.VIEWS_INDEX, model);
    }
}
