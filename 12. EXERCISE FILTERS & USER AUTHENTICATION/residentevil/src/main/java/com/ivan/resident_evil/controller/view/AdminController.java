package com.ivan.resident_evil.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends BaseController {

    @GetMapping("/admin")
    public ModelAndView adminView(Model model) {
        return renderView("views/admin", model);
    }


}
