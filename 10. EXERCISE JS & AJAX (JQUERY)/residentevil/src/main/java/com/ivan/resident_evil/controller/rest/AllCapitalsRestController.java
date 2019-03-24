package com.ivan.resident_evil.controller.rest;

import com.ivan.resident_evil.model.dto.view.CapitalViewModel;
import com.ivan.resident_evil.service.CapitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/capitals")
public class AllCapitalsRestController {

    private final CapitalService capitalService;
    private final ModelMapper modelMapper;

    @Autowired
    public AllCapitalsRestController(CapitalService capitalService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<CapitalViewModel> allCapitals() {
        return capitalService.getAll().stream()
                .map(capitalViewModel -> modelMapper.map(capitalViewModel, CapitalViewModel.class))
                .collect(Collectors.toList());
    }
}
