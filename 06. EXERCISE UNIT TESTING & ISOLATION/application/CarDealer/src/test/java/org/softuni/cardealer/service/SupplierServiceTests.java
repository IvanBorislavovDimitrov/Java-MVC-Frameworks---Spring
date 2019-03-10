package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {

    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;

    @Before
    public void initResources() {
        modelMapper = new ModelMapper();
        supplierService = new SupplierServiceImpl(supplierRepository, modelMapper);
    }

    @Test
    public void suppliesService_saveSupplierServiceModel_isProperlySaved() {
        SupplierServiceModel supplierServiceModel = getDummySupplierServiceModel();

        SupplierServiceModel expectedSupplier = supplierService.saveSupplier(supplierServiceModel);
        SupplierServiceModel actualSupplier = modelMapper.map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        assertPropertiesMatch(expectedSupplier, actualSupplier);
    }

    @Test
    public void supplierService_editSupplier_isProperlyEdited() {
        SupplierServiceModel supplierServiceModel = getDummySupplierServiceModel();

        SupplierServiceModel savedSupplier = supplierService.saveSupplier(supplierServiceModel);
        savedSupplier.setName("new_name");
        savedSupplier.setImporter(false);

        SupplierServiceModel expectedSupplier = supplierService.editSupplier(savedSupplier);
        SupplierServiceModel actualSupplier = modelMapper.map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        assertPropertiesMatch(expectedSupplier, actualSupplier);
    }

    @Test
    public void supplierService_deleteSupplier_isDeleted() {
        SupplierServiceModel supplierServiceModel = getDummySupplierServiceModel();

        SupplierServiceModel savedSupplier = supplierService.saveSupplier(supplierServiceModel);

        supplierService.deleteSupplier(savedSupplier.getId());

        Assert.assertEquals(0, supplierRepository.count());
    }

    @Test
    public void supplierService_findSupplier_isProperlyFound() {
        SupplierServiceModel supplierServiceModel = getDummySupplierServiceModel();

        SupplierServiceModel savedSupplier = supplierService.saveSupplier(supplierServiceModel);

        SupplierServiceModel expectedSupplier = supplierService.findSupplierById(savedSupplier.getId());
        SupplierServiceModel actualSupplier = modelMapper.map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        assertPropertiesMatch(expectedSupplier, actualSupplier);
    }

    private void assertPropertiesMatch(SupplierServiceModel expectedSupplier, SupplierServiceModel actualSupplier) {
        Assert.assertEquals(actualSupplier.getId(), expectedSupplier.getId());
        Assert.assertEquals(actualSupplier.getName(), expectedSupplier.getName());
        Assert.assertEquals(actualSupplier.getId(), expectedSupplier.getId());
    }

    private SupplierServiceModel getDummySupplierServiceModel() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel();
        supplierServiceModel.setImporter(true);
        supplierServiceModel.setName("test_name");

        return supplierServiceModel;
    }
}
