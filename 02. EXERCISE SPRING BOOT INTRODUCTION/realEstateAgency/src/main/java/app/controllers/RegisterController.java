package app.controllers;

import app.model.binding.OfferBindingModel;
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
public class RegisterController {

    private final ModelMapper modelMapper;
    private final OfferService offerService;

    public RegisterController(ModelMapper modelMapper, OfferService offerService) {
        this.modelMapper = modelMapper;
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public ModelAndView index() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute @Valid OfferBindingModel offerBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/register");
        }
        OfferServiceModel offerServiceModel = modelMapper.map(offerBindingModel, OfferServiceModel.class);
        offerService.save(offerServiceModel);

        return new ModelAndView("redirect:/");
    }
}
