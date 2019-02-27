package app.controllers;

import app.model.view.OfferViewModel;
import app.services.api.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public IndexController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("offers", getAllOffers());

        return new ModelAndView("index");
    }

    private List<OfferViewModel> getAllOffers() {
        return offerService.getAll().stream()
                .map(offerServiceModel -> modelMapper.map(offerServiceModel, OfferViewModel.class))
                .collect(Collectors.toList());
    }
}
