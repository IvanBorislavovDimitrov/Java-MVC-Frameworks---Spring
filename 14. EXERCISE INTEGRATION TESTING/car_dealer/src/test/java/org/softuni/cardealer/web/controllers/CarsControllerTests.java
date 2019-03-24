package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarsControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    @WithMockUser("petko")
    public void testSupplier_AddSupplier_SupplierAdded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cars/add")
                .param("travelledDistance", "12")
                .param("model", "asdsad")
                .param("make", "asdasdad"));

        Assert.assertTrue(carRepository.count() > 0);
    }

    @WithMockUser("petko")
    @Test
    public void testSupplier_DeleteSupplier_SupplierDeleted() throws Exception {
        Car car = new Car();
        car.setMake("audi");
        car.setModel("audi");
        car.setTravelledDistance(123L);
        car.setId("1");
        Car save = carRepository.save(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars/edit/" + save.getId())
                .param("travelledDistance", "12")
                .param("model", "asdsad")
                .param("make", "asdasdad"));

        Car car1 = carRepository.findById(save.getId()).get();

        Assert.assertEquals("asdsad", car1.getModel());
        Assert.assertEquals("asdasdad", car1.getMake());
        Assert.assertEquals(new Long(12), car1.getTravelledDistance());
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AllSuppliers_ReturnsAllSuppliers() throws Exception {
        mockMvc.perform(get("/cars/all"))
                .andExpect(view().name("all-cars"))
                .andExpect(model().attributeExists("cars"));
    }

}
