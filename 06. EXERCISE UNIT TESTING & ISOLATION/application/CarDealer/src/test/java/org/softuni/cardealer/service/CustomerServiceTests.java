package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTests {

    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerService customerService;

    @Before
    public void initResources() {
        modelMapper = new ModelMapper();
        customerService = new CustomerServiceImpl(customerRepository, modelMapper);
    }

    @Test
    public void customerService_saveCustomer_isProperlySaved() {
        CustomerServiceModel customerServiceModel = getDummyCustomerServiceModel();

        CustomerServiceModel expectedCustomerServiceModel = customerService.saveCustomer(customerServiceModel);
        CustomerServiceModel actualCustomerServiceModel = modelMapper.map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        assertPropertiesMatch(expectedCustomerServiceModel, actualCustomerServiceModel);
    }

    @Test
    public void customerService_editCustomer_isProperlyEdited() {
        CustomerServiceModel customerServiceModel = getDummyCustomerServiceModel();

        CustomerServiceModel savedCustomerServiceModel = customerService.saveCustomer(customerServiceModel);
        savedCustomerServiceModel.setYoungDriver(false);
        savedCustomerServiceModel.setBirthDate(LocalDate.now());
        savedCustomerServiceModel.setName("Pesho");

        CustomerServiceModel expectedCustomerServiceModel = customerService.editCustomer(savedCustomerServiceModel);
        CustomerServiceModel actualCustomerServiceModel = modelMapper.map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        assertPropertiesMatch(actualCustomerServiceModel, expectedCustomerServiceModel);
    }

    @Test
    public void customerService_deleteCustomer_isDeleted() {
        CustomerServiceModel customerServiceModel = getDummyCustomerServiceModel();

        CustomerServiceModel savedCustomer = customerService.saveCustomer(customerServiceModel);

        customerService.deleteCustomer(savedCustomer.getId());

        Assert.assertEquals(0, customerRepository.count());
    }

    @Test
    public void customerService_findById_isProperlyFound() {
        CustomerServiceModel customerServiceModel = getDummyCustomerServiceModel();

        CustomerServiceModel expectedCustomerServiceModel = customerService.saveCustomer(customerServiceModel);

        CustomerServiceModel actualCustomerServiceModel = customerService.findCustomerById(expectedCustomerServiceModel.getId());

        assertPropertiesMatch(actualCustomerServiceModel, expectedCustomerServiceModel);
    }

    private void assertPropertiesMatch(CustomerServiceModel actualCustomerServiceModel, CustomerServiceModel expectedCustomerServiceModel) {
        Assert.assertEquals(actualCustomerServiceModel.getBirthDate(), expectedCustomerServiceModel.getBirthDate());
        Assert.assertEquals(actualCustomerServiceModel.getId(), expectedCustomerServiceModel.getId());
        Assert.assertEquals(actualCustomerServiceModel.getName(), expectedCustomerServiceModel.getName());
    }

    private CustomerServiceModel getDummyCustomerServiceModel() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setBirthDate(LocalDate.now());
        customerServiceModel.setName("Mitko");
        customerServiceModel.setYoungDriver(true);

        return customerServiceModel;
    }
}
