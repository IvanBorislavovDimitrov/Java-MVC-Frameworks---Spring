package com.ivan.resident_evil.controller.rest;

import com.ivan.resident_evil.model.dto.rest.VirusRestViewModel;
import com.ivan.resident_evil.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/viruses")
public class AllVirusesRestController {

    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public AllVirusesRestController(VirusService virusService, ModelMapper modelMapper) {
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VirusRestViewModel> allViruses() {
        return virusService.getAll().stream()
                .map(virusViewModel -> modelMapper.map(virusViewModel, VirusRestViewModel.class))
                .collect(Collectors.toList());
    }
}
