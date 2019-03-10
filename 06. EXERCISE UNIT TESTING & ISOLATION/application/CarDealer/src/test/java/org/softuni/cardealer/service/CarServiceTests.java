package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTests {

    @Autowired
    private CarRepository carRepository;
    private ModelMapper modelMapper;
    private CarService carService;

    @Before
    public void initResources() {
        modelMapper = new ModelMapper();
        carService = new CarServiceImpl(carRepository, modelMapper);
    }

    @Test
    public void carService_saveCar_isProperlySaved() {
        CarServiceModel carServiceModel = getDummyCarServiceModel();

        CarServiceModel expectedCarServiceModel = carService.saveCar(carServiceModel);
        CarServiceModel actualCarServiceModel = modelMapper.map(carRepository.findAll().get(0), CarServiceModel.class);

        assertPropertiesMatch(actualCarServiceModel, expectedCarServiceModel);
    }

    @Test
    public void carService_editCar_isProperlyEdited() {
        CarServiceModel carServiceModel = getDummyCarServiceModel();

        CarServiceModel savedCar = carService.saveCar(carServiceModel);
        savedCar.setTravelledDistance(1000L);
        savedCar.setMake("Lada");
        savedCar.setModel("Niva");
        CarServiceModel expectedCarServiceModel = carService.editCar(savedCar);
        CarServiceModel actualCarServiceModel = modelMapper.map(carRepository.findAll().get(0), CarServiceModel.class);

        assertPropertiesMatch(actualCarServiceModel, expectedCarServiceModel);
    }

    @Test
    public void carService_deleteCar_isDeleted() {
        CarServiceModel carServiceModel = getDummyCarServiceModel();

        CarServiceModel savedCar = carService.saveCar(carServiceModel);

        carService.deleteCar(savedCar.getId());

        Assert.assertEquals(0, carRepository.count());
    }

    @Test
    public void carService_findById_isProperlyFound() {
        CarServiceModel carServiceModel = getDummyCarServiceModel();

        CarServiceModel expectedCarServiceModel = carService.saveCar(carServiceModel);

        CarServiceModel actualCarServiceModel = carService.findCarById(expectedCarServiceModel.getId());

        assertPropertiesMatch(actualCarServiceModel, expectedCarServiceModel);
    }

    private void assertPropertiesMatch(CarServiceModel actualCarServiceModel, CarServiceModel expectedCarServiceModel) {
        Assert.assertEquals(actualCarServiceModel.getMake(), expectedCarServiceModel.getMake());
        Assert.assertEquals(actualCarServiceModel.getId(), expectedCarServiceModel.getId());
        Assert.assertEquals(actualCarServiceModel.getModel(), expectedCarServiceModel.getModel());
        Assert.assertEquals(actualCarServiceModel.getTravelledDistance(), expectedCarServiceModel.getTravelledDistance());
    }

    private CarServiceModel getDummyCarServiceModel() {
        CarServiceModel carServiceModel = new CarServiceModel();
        carServiceModel.setMake("Dacia");
        carServiceModel.setModel("Sandero");
        carServiceModel.setTravelledDistance(100_000_000L);

        return carServiceModel;
    }
}
