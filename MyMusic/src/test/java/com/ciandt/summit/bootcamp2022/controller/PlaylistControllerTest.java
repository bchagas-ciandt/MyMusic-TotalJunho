package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.*;
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
import java.util.Arrays;
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
        this.mockMvc.perform(get("http://localhost:8080/api/v1/playlists/findAll")).andExpect(status().isOk());
    }

    @Test
    void findPlaylistByIdShouldReturnStatus400WhenJSONIsInvalid() throws Exception {

        MvcResult mvcResult =  this.mockMvc.perform(post("http://localhost:8080/api/v1/playlists/a39926f4-6acb-4497-884f-d4e5296/dd444a81-9588-4e6b-9d3d-1f1036a6eaa1/musicas")).andExpect(status().isBadRequest()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Formato JSON inválido, consultar documentação"));

    }

    @Test
    void findPlaylistByIdShouldReturnStatusOkWhenPostingNewMusics() throws Exception {

        String jsonInput = " {\n     \"id\": \"string\", \n     \"name\": \"string\",\n" +
                "     \"artist\": {\n       \"id\": \"string\", \n       \"name\": \"string\" \n      } \n" +
                "  }\n";

        this.mockMvc.perform(post("http://localhost:8080/api/v1/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf422/dd444a81-9588-4e6b-9d3d-1f1036a6eaa1/musicas")
                .contentType(MediaType.APPLICATION_JSON).content(jsonInput))
                .andExpect(status().isOk());

    }

    @Test
    void findPlaylistByIdShouldReturnStatusOk() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/api/v1/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf422"))
                .andExpect(status().isOk());

    }

    @Test
    void addMusicsToPlaylistShouldReturnErrorWhenIdDoesNotExists(){

        Music newMusic = new Music();

        when(this.service.addMusicToPlaylist("7ff43fef-2d9f-4842-a23a-4be8b35bf42","", newMusic))
                .thenThrow(PlaylistNotFoundException.class);

        given().accept(ContentType.JSON)
                .when().post("http://localhost:8080/api/v1/playlists/7ff43fef-2d9f-4842-a23a-4be8b35bf42/dd444a81-9588-4e6b-9d3d-1f1036a6eaa1/musicas", newMusic)
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void addMusicFromPlaylistShouldReturnStatusOk() throws Exception {

        Music music = new Music("musicId", "The music", new Artist("artistId","Name Artist"));
        List<Music> musicList = new ArrayList<>();
        musicList.add(music);
        User user = new User();
        user.setId("userId");
        Playlist playlist = new Playlist("playlistId",musicList);

        given(this.service.addMusicToPlaylist("playlistId","userId", music)).willReturn("Música adicionada com sucesso!");

        String requestJson = " {\n     \"id\": \"musicId\", \n     \"name\": \"The music\",\n" +
                "     \"artist\": {\n       \"id\": \"artistId\", \n       \"name\": \"Name Artist\" \n      } \n" +
                "  }";

        RequestBuilder request = post("/api/v1/playlists/playlistId/userId/musicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals("Música adicionada com sucesso!",
                result.getResponse().getContentAsString());
    }

//    @Test
//    void addMusicFromPlaylistShouldThrowsExceptionWhenCommonUserExceedLimit() throws Exception {
//        Music music1 = new Music("music1Id", "The music 1", new Artist("artist1Id","Name Artist 1"));
//        Music music2 = new Music("music2Id", "The music 2", new Artist("artist2Id","Name Artist 2"));
//        Music music3 = new Music("music3Id", "The music 3", new Artist("artist3Id","Name Artist 3"));
//        Music music4 = new Music("music4Id", "The music 4", new Artist("artist4Id","Name Artist 4"));
//        Music music5 = new Music("music5Id", "The music 5", new Artist("artist5Id","Name Artist 5"));
//        Music music6 = new Music("music6Id", "The music 6", new Artist("artist6Id","Name Artist 6"));
//        List<Music> musicList = new ArrayList<>(Arrays.asList(music1, music2, music3, music4, music5));
//        User user = new User();
//        user.setId("userId");
//        user.setUserType(new UserType("userTypeId","Comum"));
//        Playlist playlist = new Playlist("playlistId",musicList);
//
//        given(this.service.addMusicToPlaylist("playlistId","userId", music6))
//                .willReturn("Você atingiu o número máximo de músicas em sua playlist.Para adicionar mais músicas contrate o plano Premium.");
//
//        String requestJson = " {\n     \"id\": \"music6Id\", \n     \"name\": \"The music 6\",\n" +
//                "     \"artist\": {\n       \"id\": \"artis6tId\", \n       \"name\": \"Name Artist 6\" \n      } \n" +
//                "  }";
//
//        RequestBuilder request = post("/api/v1/playlists/playlistId/userId/musicas")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson);
//
//        MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
//
//        assertEquals("Você atingiu o número máximo de músicas em sua playlist.Para adicionar mais músicas contrate o plano Premium.",
//                result.getResponse().getContentAsString());
//    }

    @Test
    void removeMusicFromPlaylistShouldReturnStatusOk() throws Exception {

        String playlist = "playlistId";
        String musica = "musicId";

        this.service.removeMusicFromPlaylist(playlist, musica);

        RequestBuilder request = put("/api/v1/playlists/playlistId/musicas/musicId");

        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals("",
                result.getResponse().getContentAsString());

    }

}