package com.ivan.resident_evil.controller.view;

import com.ivan.resident_evil.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnauthorizedController extends BaseController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(Model model) {
        return renderView(Constants.UNAUTHORIZED, model);
    }
}
