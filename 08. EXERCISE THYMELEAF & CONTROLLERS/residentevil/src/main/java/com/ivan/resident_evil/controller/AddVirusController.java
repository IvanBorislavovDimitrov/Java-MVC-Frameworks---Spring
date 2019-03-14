package com.ivan.resident_evil.controller;

import com.ivan.resident_evil.constants.Constants;
import com.ivan.resident_evil.model.dto.binding.VirusAddBindingModel;
import com.ivan.resident_evil.model.dto.service.VirusRegisterServiceModel;
import com.ivan.resident_evil.model.dto.view.CapitalViewModel;
import com.ivan.resident_evil.service.CapitalService;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/viruses")
public class AddVirusController extends BaseController {

    private final VirusService virusService;
    private final CapitalService capitalService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddVirusController(VirusService virusService, CapitalService capitalService, ModelMapper modelMapper) {
        this.virusService = virusService;
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView addVirus(Model model) {
        return addVirusView(model);
    }

    @PostMapping("/add")
    public ModelAndView addVirusConfirm(@ModelAttribute("virus") @Valid VirusAddBindingModel virusAddBindingModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return addVirusView(model);
        }

        virusService.save(modelMapper.map(virusAddBindingModel, VirusRegisterServiceModel.class));

        return redirect(Constants.REDIRECT_INDEX);
    }

    private ModelAndView addVirusView(Model model) {
        List<String> capitals = capitalService.getAll().stream()
                .map(CapitalViewModel::getName)
                .collect(Collectors.toList());
        model.addAttribute(Constants.CAPITALS, capitals);

        return renderView(Constants.ADD_VIRUS, model);
    }

    @ModelAttribute("virus")
    public VirusAddBindingModel virusAddBindingModel() {
        return new VirusAddBindingModel();
    }
}
