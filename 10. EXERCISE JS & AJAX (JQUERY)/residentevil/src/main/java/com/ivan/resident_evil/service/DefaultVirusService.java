package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.Capital;
import com.ivan.resident_evil.model.Virus;
import com.ivan.resident_evil.model.dto.service.VirusRegisterServiceModel;
import com.ivan.resident_evil.model.dto.view.VirusViewModel;
import com.ivan.resident_evil.model.enums.Creator;
import com.ivan.resident_evil.model.enums.Magnitude;
import com.ivan.resident_evil.model.enums.Mutation;
import com.ivan.resident_evil.repository.CapitalRepository;
import com.ivan.resident_evil.repository.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultVirusService implements VirusService {

    private final VirusRepository virusRepository;
    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    public DefaultVirusService(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusRegisterServiceModel save(VirusRegisterServiceModel virusRegisterServiceModel) {
        Virus virus = modelMapper.map(virusRegisterServiceModel, Virus.class);
        String[] dateParts = virusRegisterServiceModel.getDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        Date date = new GregorianCalendar(year, month, day).getTime();
        virus.setReleasedOn(date);

        List<Capital> capitals = capitalRepository
                .findByNameIn(virusRegisterServiceModel.getAffectedCapitals());
        capitals.forEach(c -> c.setVirus(virus));

        virus.setCapitals(capitals);
        return modelMapper.map(virusRepository.saveAndFlush(virus), VirusRegisterServiceModel.class);
    }

    @Override
    public List<VirusViewModel> getAll() {
        return virusRepository.findAll().stream()
                .map(virus -> modelMapper.map(virus, VirusViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void edit(String id, VirusRegisterServiceModel virusRegisterServiceModel) {
        Virus virus = virusRepository.findById(id).orElse(null);
        List<Capital> capitals = capitalRepository
                .findByNameIn(virusRegisterServiceModel.getAffectedCapitals());
        capitals.forEach(c -> c.setVirus(virus));

        virus.setCapitals(capitals);
        String[] dateParts = virusRegisterServiceModel.getDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        Date date = new GregorianCalendar(year, month, day).getTime();
        virus.setReleasedOn(date);

        virus.setCreator(Creator.valueOf(virusRegisterServiceModel.getCreator()));
        virus.setCurable(virusRegisterServiceModel.getCurable());
        virus.setDeadly(virusRegisterServiceModel.getDeadly());
        virus.setDescription(virusRegisterServiceModel.getDescription());
        virus.setHoursUntilTurn((byte) virusRegisterServiceModel.getHoursUntilTurn());
        virus.setMagnitude(Magnitude.valueOf(virusRegisterServiceModel.getMagnitude()));
        virus.setMutation(Mutation.valueOf(virusRegisterServiceModel.getMutation()));
        virus.setName(virusRegisterServiceModel.getName());
        virus.setSideEffects(virus.getSideEffects());
        virus.setTurnoverRate(BigDecimal.valueOf(virusRegisterServiceModel.getTurnoverRate()));

        virusRepository.saveAndFlush(virus);
    }

    @Override
    public void deleteById(String id) {
        virusRepository.deleteById(id);
    }
}
