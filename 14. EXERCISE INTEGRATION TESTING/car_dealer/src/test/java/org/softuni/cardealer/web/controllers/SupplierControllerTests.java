package org.softuni.cardealer.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Before
    public void setUp() {
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AllSuppliersFetch_ReturnsAllSuppliers() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId("1");
        supplier.setIsImporter(true);
        supplier.setName("name");

        Supplier saved = supplierRepository.save(supplier);
        String savedJSON = new ObjectMapper().writeValueAsString(Arrays.asList(saved));
        String contentAsString = mockMvc.perform(get("/suppliers/fetch"))
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(contentAsString.contains(savedJSON.substring(3, savedJSON.length() - 3)));
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_AddSupplier_SupplierAdded() throws Exception {
        mockMvc.perform(post("/suppliers/add")
                .param("name", "dfdsaf")
                .param("isImporter", "true"));

        Assert.assertTrue(supplierRepository.count() > 0);
    }

    @Test
    @WithMockUser("petko")
    public void testSupplier_EditSupplier_SupplierEdited() throws Exception {
        mockMvc.perform(post("/suppliers/add")
                .param("name", "nekuv")
                .param("isImporter", "true"));
        Supplier nekuv = supplierRepository.findByName("nekuv").get();
        mockMvc.perform(post("/suppliers/edit/" + nekuv.getId())
                .param("name", "novoto")
                .param("isImporter", "false"));
        Supplier nov = supplierRepository.findByName("novoto").orElse(null);

        Assert.assertNotNull(nov);
        Assert.assertEquals("novoto", nov.getName());
        Assert.assertEquals("false", String.valueOf(nov.getIsImporter()));
    }

    @WithMockUser("petko")
    @Test
    public void testSupplier_DeleteSupplier_SupplierDeleted() throws Exception {
        mockMvc.perform(post("/suppliers/add")
                .param("name", "nekuv")
                .param("isImporter", "true"));
        Supplier nekuv = supplierRepository.findByName("nekuv").get();
        mockMvc.perform(post("/suppliers/delete/" + nekuv.getId()));
        Supplier nov = supplierRepository.findByName("nekuv").orElse(null);
        Assert.assertNull(nov);
    }



    @Test
    @WithMockUser("petko")
    public void testSupplier_AllSuppliers_ReturnsAllSuppliers() throws Exception {
        mockMvc.perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }


}
