package com.ivan.resident_evil.controller;

import com.ivan.resident_evil.constants.Constants;
import com.ivan.resident_evil.model.dto.view.VirusViewModel;
import com.ivan.resident_evil.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AllVirusesController extends BaseController {

    private final VirusService virusService;

    @Autowired
    public AllVirusesController(VirusService virusService, ModelMapper modelMapper) {
        this.virusService = virusService;
    }

    @GetMapping("/viruses/show")
    public ModelAndView allViruses(Model model) {
        List<VirusViewModel> viruses = virusService.getAll();
        model.addAttribute(Constants.VIRUSES, viruses);
        return renderView(Constants.ALL_VIRUSES, model);
    }
}
