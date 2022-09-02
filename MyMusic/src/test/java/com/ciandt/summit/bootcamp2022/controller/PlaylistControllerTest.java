package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.PlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PlaylistController.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaylistService service;
    @Autowired
    private PlaylistController playlistController;

    @BeforeEach
    void setup() {
        standaloneSetup(this.playlistController);
    }

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

    @Test
    void addMusicsToPlaylistShouldReturnErrorWhenIdDoesNotExists(){

        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<Music>());

        when(this.service.addMusicToPlaylist("7ff43fef-2d9f-4842-a23a-4be8b35bf42", objectDTO))
                .thenThrow(PlaylistNotFoundException.class);

        given().accept(ContentType.JSON)
                .when().post("http://localhost:8080/api/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf42/musicas", objectDTO)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }
    @Test
    void addMusicFromPlaylistShouldReturnStatusOk() throws Exception {

        Music music = new Music("musicId", "The music", new Artist("artistId","Name Artist"));
        List<Music> musicList = new ArrayList<>();
        musicList.add(music);
        ObjectDTO objectDTO = new ObjectDTO(musicList);
        Playlist playlist = new Playlist("playlistId",musicList);

        given(this.service.addMusicToPlaylist("playlistId", objectDTO)).willReturn(playlist);

        String requestJson = "{\n  \"data\": [\n  {\n     \"id\": \"musicId\", \n     \"name\": \"The music\",\n" +
                "     \"artist\": {\n       \"id\": \"artistId\", \n       \"name\": \"Name Artist\" \n      } \n" +
                "  }\n]}\n";

        RequestBuilder request = post("/api/playlists/playlistId/musicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals("",
                result.getResponse().getContentAsString());
    }

    @Test
    void removeMusicFromPlaylistShouldReturnStatusOk() throws Exception {

        String playlist = "playlistId";
        String musica = "musicId";

        this.service.removeMusicFromPlaylist(playlist, musica);

        RequestBuilder request = put("/api/playlists/playlistId/musicas/musicId");

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals("",
                result.getResponse().getContentAsString());

    }

}