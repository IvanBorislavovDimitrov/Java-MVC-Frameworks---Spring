package com.ivan.services.impl;

import com.ivan.entities.Document;
import com.ivan.models.service.DocumentRegisterServiceModel;
import com.ivan.models.service.DocumentServiceModel;
import com.ivan.repositories.DocumentRepository;
import com.ivan.services.api.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocumentServiceModel> getAll() {
        return documentRepository.findAll().stream()
                .map(document -> modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel save(DocumentRegisterServiceModel documentRegisterServiceModel) {
        return modelMapper.map(documentRepository.save(modelMapper.map(documentRegisterServiceModel, Document.class)),
                DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel getById(String id) {
        Optional<Document> doc = documentRepository.findById(id);
        return doc
                .map(document -> modelMapper.map(document, DocumentServiceModel.class))
                .orElse(null);

    }

    @Override
    public void deleteById(String id) {
        documentRepository.deleteById(id);
    }
}
