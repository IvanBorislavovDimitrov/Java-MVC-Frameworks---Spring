package org.softuni.cardealer.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HomeControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testIndex_Index_returnsIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser("pesho")
    public void testHome_Home_ReturnsHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(view().name("home"));
    }
}
