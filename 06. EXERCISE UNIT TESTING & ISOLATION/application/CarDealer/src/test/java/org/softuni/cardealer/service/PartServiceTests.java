package org.softuni.cardealer.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceTests {

    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private PartService partService;

    @Before
    public void initResources() {
        modelMapper = new ModelMapper();
        partService = new PartServiceImpl(partRepository, modelMapper);
    }

    @Test
    public void partService_savePart_isProperlySaved() {

        PartServiceModel partServiceModel = getDummyPartServiceModel();

        PartServiceModel expectedPartServiceModel = partService.savePart(partServiceModel);
        PartServiceModel actualPartServiceModel = modelMapper.map(partRepository.findAll().get(0), PartServiceModel.class);

        assertPropertiesMatch(expectedPartServiceModel, actualPartServiceModel);
    }

    @Test
    public void partService_editPart_isProperlyEdited() {
        PartServiceModel partServiceModel = getDummyPartServiceModel();

        PartServiceModel savedPartServiceModel = partService.savePart(partServiceModel);
        savedPartServiceModel.setPrice(BigDecimal.TEN);
        savedPartServiceModel.setName("Pesho");

        PartServiceModel expectedPartServiceModel = partService.editPart(savedPartServiceModel);
        PartServiceModel actualPartServiceModel = modelMapper.map(partRepository.findAll().get(0), PartServiceModel.class);

        assertPropertiesMatch(actualPartServiceModel, expectedPartServiceModel);
    }

    @Test
    public void partService_deletePart_isDeleted() {
        PartServiceModel partServiceModel = getDummyPartServiceModel();

        PartServiceModel savedPart = partService.savePart(partServiceModel);

        partService.deletePart(savedPart.getId());

        Assert.assertEquals(0, partRepository.count());
    }

    @Test
    public void partService_findById_isProperlyFound() {
        PartServiceModel partServiceModel = getDummyPartServiceModel();

        PartServiceModel expectedPartServiceModel = partService.savePart(partServiceModel);

        PartServiceModel actualPartServiceModel = partService.findPartById(expectedPartServiceModel.getId());

        assertPropertiesMatch(actualPartServiceModel, expectedPartServiceModel);
    }

    private void assertPropertiesMatch(PartServiceModel actualPartServiceModel, PartServiceModel expectedPartServiceModel) {
        Assert.assertEquals(actualPartServiceModel.getPrice(), expectedPartServiceModel.getPrice());
        Assert.assertEquals(actualPartServiceModel.getName(), expectedPartServiceModel.getName());
    }

    private PartServiceModel getDummyPartServiceModel() {
        PartServiceModel partServiceModel = new PartServiceModel();
        partServiceModel.setPrice(BigDecimal.valueOf(1));
        partServiceModel.setName("Airbag");

        return partServiceModel;
    }

}
