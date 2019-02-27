package app.controllers;

import app.model.binding.FindOfferBindingModel;
import app.model.service.FindOfferServiceModel;
import app.model.service.OfferServiceModel;
import app.services.api.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FindController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public FindController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/find")
    public ModelAndView find() {
        return new ModelAndView("find");
    }

    @PostMapping("/find")
    public ModelAndView findSubmit(@ModelAttribute @Valid FindOfferBindingModel findOfferBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/find");
        }

        try {
            offerService.findAndRemove(modelMapper.map(findOfferBindingModel, FindOfferServiceModel.class));
        } catch (IllegalArgumentException e) {
            return new ModelAndView("redirect:/find");
        }

        return new ModelAndView("redirect:/");
    }
}
