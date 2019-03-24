package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsersControllerTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public UserRepository userRepository;

    @Before
    public synchronized void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testUsersController_GetLogin_ReturnsLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void testUsersController_GetRegister_ReturnsLoginPage() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test
    public void testUsersController_PostRegister_CreatesUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", "dfdsaf")
                .param("password", "123")
                .param("confirmPassword", "123")
                .param("email", "fdsaf@pesho.com"));

        Assert.assertEquals(1, userRepository.count());
    }

    @Test
    public void testUsersController_PostRegister_Redirect() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", "mitko")
                .param("password", "123")
                .param("confirmPassword", "123")
                .param("email", "email@pesho.com"))
                .andExpect(view().name("redirect:login"));

    }
}
