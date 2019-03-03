package com.ivan.services.api;

import com.ivan.models.service.DocumentRegisterServiceModel;
import com.ivan.models.service.DocumentServiceModel;
import com.ivan.models.view.DocumentViewModel;

import java.util.List;

public interface DocumentService {

    List<DocumentServiceModel> getAll();

    DocumentServiceModel save(DocumentRegisterServiceModel documentRegisterServiceModel);

    DocumentServiceModel getById(String id);

    void deleteById(String id);

}
