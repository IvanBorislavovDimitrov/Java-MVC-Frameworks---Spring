package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.dto.service.VirusRegisterServiceModel;
import com.ivan.resident_evil.model.dto.view.CapitalViewModel;
import com.ivan.resident_evil.model.dto.view.VirusViewModel;

import java.util.List;

public interface VirusService {

    VirusRegisterServiceModel save(VirusRegisterServiceModel virusRegisterServiceModel);

    List<VirusViewModel> getAll();

    void edit(String id, VirusRegisterServiceModel vieVirusRegisterServiceModel);

    void deleteById(String id);
}
