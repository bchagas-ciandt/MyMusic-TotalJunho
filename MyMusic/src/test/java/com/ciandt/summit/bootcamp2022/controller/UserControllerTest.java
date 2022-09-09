package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private UserController userController;

    @BeforeEach
    void setup() {
        standaloneSetup(this.userController);
    }

    @Test
    void findAllShouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/v1/users")).andExpect(status().isOk());
    }

    @Test
    void findUserByIdShouldReturnStatusOk() throws Exception{
        User user = new User();
        user.setId("userId");
        BDDMockito.when(userService.findUserById("userId")).thenReturn(Optional.of(user));
        this.mockMvc.perform(get("http://localhost:8080/api/v1/users/userId"))
                .andExpect(status().isOk());
    }

}
