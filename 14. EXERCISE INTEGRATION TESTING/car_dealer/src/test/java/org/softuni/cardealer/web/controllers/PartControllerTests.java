package org.softuni.cardealer.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public PartRepository partRepository;

    @Autowired
    public SupplierRepository supplierRepository;

    @Before
    public synchronized void setUp() {
        partRepository.deleteAll();
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AllSuppliersFetch_ReturnsAllSuppliers() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId("1");
        supplier.setName("name");
        supplier.setIsImporter(false);
        Supplier savedSupplier = supplierRepository.save(supplier);

        Part part = new Part();
        part.setId("1");
        part.setName("part");
        part.setPrice(new BigDecimal("10.00"));
        part.setSupplier(savedSupplier);

        Part save = partRepository.save(part);
        String savedJSON = new ObjectMapper().writeValueAsString(Arrays.asList(save));
        String contentAsString = mockMvc.perform(get("/parts/fetch"))
                .andReturn().getResponse().getContentAsString();
        Assert.assertEquals(savedJSON, contentAsString);
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AddSupplier_SupplierAdded() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId("2");
        supplier.setName("supata");
        supplier.setIsImporter(false);
        Supplier savedSupplier = supplierRepository.save(supplier);

        mockMvc.perform(post("/parts/add")
                .param("name", "dfdsaf")
                .param("price", "10")
                .param("supplier", "supata"));

        Assert.assertTrue(partRepository.count() > 0);
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_EditSupplier_SupplierEdited() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId("1");
        supplier.setName("sup");
        supplier.setIsImporter(false);
        Supplier savedSupplier = supplierRepository.save(supplier);

        mockMvc.perform(post("/parts/add")
                .param("name", "nekuv")
                .param("price", "10")
                .param("supplier", "sup"));

        Part nekuv = partRepository.findAll().stream().filter(x -> x.getName().equals("nekuv"))
                .collect(Collectors.toList()).get(0);

        mockMvc.perform(post("/parts/edit/" + nekuv.getId())
                .param("name", "a")
                .param("price", "10")
                .param("supplier", "sup"));

        Part nov = partRepository.findAll().stream().filter(x -> x.getName().equals("a"))
                .collect(Collectors.toList()).get(0);

        Assert.assertNotNull(nov);
        Assert.assertEquals("a", nov.getName());
    }

    @WithMockUser("petko")
    @Test
    public void testSupplier_DeleteSupplier_SupplierDeleted() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId("1");
        supplier.setName("sup");
        supplier.setIsImporter(false);
        Supplier savedSupplier = supplierRepository.save(supplier);

        Part part = new Part();
        part.setName("name");
        part.setSupplier(savedSupplier);
        part.setPrice(new BigDecimal("10.00"));
        partRepository.save(part);

        mockMvc.perform(post("/parts/delete/" + part.getId()));
        List nov = partRepository.findAll().stream().filter(x -> x.getName().equals("name"))
                .collect(Collectors.toList());
        Assert.assertTrue(nov .isEmpty());
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AllSuppliers_ReturnsAllSuppliers() throws Exception {
        mockMvc.perform(get("/parts/all"))
                .andExpect(view().name("all-parts"))
                .andExpect(model().attributeExists("parts"));
    }

}
