package com.ivan.resident_evil.controller;

import com.ivan.resident_evil.constants.Constants;
import com.ivan.resident_evil.model.dto.binding.VirusAddBindingModel;
import com.ivan.resident_evil.service.VirusService;
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

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/viruses")
public class VirusController extends BaseController {

    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(VirusService virusService, ModelMapper modelMapper) {
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView addVirus(Model model) {
        return renderView(Constants.ADD_VIRUS, model);
    }

    @PostMapping("/add")
    public ModelAndView addVirusConfirm(@ModelAttribute("virus") @Valid VirusAddBindingModel virusAddBindingModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return renderView(Constants.ADD_VIRUS, model);
        }

        return redirect(Constants.REDIRECT_INDEX);
    }

    @ModelAttribute("virus")
    public VirusAddBindingModel virusAddBindingModel() {
        return new VirusAddBindingModel();
    }
}
