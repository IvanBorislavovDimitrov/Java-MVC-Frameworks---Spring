package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.dto.view.CapitalViewModel;
import com.ivan.resident_evil.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCapitalService implements CapitalService {

    private final CapitalRepository capitalRepository;

    @Autowired
    public DefaultCapitalService(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }

    @Override
    public List<CapitalViewModel> getAll() {
        return capitalRepository.findAll().stream()
                .map(c -> new CapitalViewModel() {{
                    setName(c.getName());
                }})
                .collect(Collectors.toList());
    }
}
