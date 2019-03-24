package com.ivan.resident_evil.controller.view;

import com.ivan.resident_evil.constants.Constants;
import com.ivan.resident_evil.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/viruses")
public class DeleteController extends BaseController {

    private final VirusService virusService;

    @Autowired
    public DeleteController(VirusService virusService) {
        this.virusService = virusService;
    }

    @GetMapping("/delete")
    public ModelAndView deleteVirus(@RequestParam("id") String id) {
        virusService.deleteById(id);

        return redirect(Constants.REDIRECT_INDEX);
    }
}
