package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.*;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceTests {

    @Autowired
    private CarSaleRepository carSaleRepository;

    @Autowired
    private PartSaleRepository partSaleRepository;
    private ModelMapper modelMapper;
    private SaleService saleService;

    @Before
    public void initResources() {
        modelMapper = new ModelMapper();
        saleService = new SaleServiceImpl(carSaleRepository, partSaleRepository, modelMapper);
    }

    @Test
    public void saleService_salePart_isSold() {
        PartSaleServiceModel saleServiceModel = getDummyPartSaleServiceModel();

        PartSaleServiceModel expectedPartSaleServiceModel = saleService.salePart(saleServiceModel);

        PartSaleServiceModel actualPartServiceModel = modelMapper.map(partSaleRepository.findAll().get(0), PartSaleServiceModel.class);

        assertPropertiesMatch(expectedPartSaleServiceModel, actualPartServiceModel);
    }

    @Test
    public void saleService_saleCar_isSold() {
        CarSaleServiceModel carSaleServiceModel = getDummyCarSaleServiceModel();

        CarSaleServiceModel expectedCarSaleServiceModel = saleService.saleCar(carSaleServiceModel);

        CarSaleServiceModel actualCarServiceModel = modelMapper.map(carSaleRepository.findAll().get(0), CarSaleServiceModel.class);
        assertPropertiesMatch(expectedCarSaleServiceModel, actualCarServiceModel);
    }


    private void assertPropertiesMatch(PartSaleServiceModel expectedSaleServiceModel, PartSaleServiceModel actualSaleServiceModel) {
        Assert.assertEquals(actualSaleServiceModel.getId(), expectedSaleServiceModel.getId());
        Assert.assertEquals(actualSaleServiceModel.getQuantity(), expectedSaleServiceModel.getQuantity());
    }

    private void assertPropertiesMatch(CarSaleServiceModel expectedCarSaleServiceModel, CarSaleServiceModel actualCarSaleServiceModel) {
        Assert.assertEquals(actualCarSaleServiceModel.getId(), expectedCarSaleServiceModel.getId());
        Assert.assertEquals(actualCarSaleServiceModel.getDiscount(), expectedCarSaleServiceModel.getDiscount());
    }

    private CarSaleServiceModel getDummyCarSaleServiceModel() {
        CarSaleServiceModel saleServiceModel = new CarSaleServiceModel();
        saleServiceModel.setDiscount(10D);

        return saleServiceModel;
    }

    private PartSaleServiceModel getDummyPartSaleServiceModel() {
        PartSaleServiceModel saleServiceModel = new PartSaleServiceModel();
        saleServiceModel.setDiscount(10D);
        saleServiceModel.setQuantity(10);

        return saleServiceModel;
    }
}
