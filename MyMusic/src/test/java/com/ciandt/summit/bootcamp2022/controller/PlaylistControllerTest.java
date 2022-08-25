package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaylistController.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaylistService service;

    @Test
    void findAllShouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/playlists/findAll")).andExpect(status().isOk());
    }

    @Test
    void findPlaylistByIdShouldReturnStatus400WhenJSONIsInvalid() throws Exception {

        MvcResult mvcResult =  this.mockMvc.perform(post("http://localhost:8080/api/playlists/a39926f4-6acb-4497-884f-d4e5296/musicas")).andExpect(status().isBadRequest()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Formato JSON inválido, consultar documentação"));

    }

    @Test
    void findPlaylistByIdShouldReturnStatusOkWhenPostingNewMusics() throws Exception {

        String jsonInput = "{\n  \"data\": [\n  {\n     \"id\": \"string\", \n     \"name\": \"string\",\n" +
                "     \"artist\": {\n       \"id\": \"string\", \n       \"name\": \"string\" \n      } \n" +
                "  }\n]}\n";

        this.mockMvc.perform(post("http://localhost:8080/api/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf422/musicas")
                .contentType(MediaType.APPLICATION_JSON).content(jsonInput))
                .andExpect(status().isOk());

    }

    @Test
    void findPlaylistByIdShouldReturnStatusOk() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/api/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf422"))
                .andExpect(status().isOk());

    }
}