package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.dto.view.CapitalViewModel;
import com.ivan.resident_evil.repository.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalViewModel> getAll() {
        return capitalRepository.findAll().stream()
                .map(capital ->  modelMapper.map(capital, CapitalViewModel.class))
                .collect(Collectors.toList());
    }
}
