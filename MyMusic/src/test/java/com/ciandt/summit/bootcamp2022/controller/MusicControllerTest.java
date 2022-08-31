package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class MusicControllerTest {

    @InjectMocks
    private MusicController musicController;
    @Mock
    private MusicService musicService ;

    @BeforeEach
    public void setup(){
        List<Music> musics = new ArrayList<>(Arrays.asList(new Music("asdasdaasdasdasd", "Another brick in the wall", new Artist("182738192738921398123", "pink floyd"))));
        ObjectDTO objectDTO = new ObjectDTO(musics);
        BDDMockito.when(musicService.findMusicsByMusicNameOrArtistName("pink floyd")).thenReturn(objectDTO);
        BDDMockito.when(musicService.findMusicsByMusicNameOrArtistName("Another brick in the wall")).thenReturn(objectDTO);
        BDDMockito.when(musicService.findMusicsByMusicNameOrArtistName("pi")).thenThrow(InvalidFilterException.class);
        BDDMockito.when(musicService.findMusicsByMusicNameOrArtistName("filtroinvalido")).thenThrow(EmptyListException.class);
        BDDMockito.when(musicService.findMusicsWithoutParameters()).thenReturn(objectDTO);
    }

    @Test
    @DisplayName("return a object with a list of musics when music with artist name exists")
    void findMusicsByMusicNameOrArtistName_shouldReturnListOfMusic_WhenArtistNameIsValid() {
        ObjectDTO objectDTO = musicController.findMusicsByMusicNameOrArtistName("pink floyd").getBody();

        Assertions.assertEquals(1, objectDTO.getData().size());
        Assertions.assertFalse(objectDTO.getData().isEmpty());
        Assertions.assertNotNull(objectDTO);
        Assertions.assertEquals("asdasdaasdasdasd", objectDTO.getData().get(0).getId());
        Assertions.assertEquals("Another brick in the wall", objectDTO.getData().get(0).getName());
        Assertions.assertEquals("182738192738921398123", objectDTO.getData().get(0).getArtist().getId());
        Assertions.assertEquals("pink floyd", objectDTO.getData().get(0).getArtist().getName());
    }

    @Test
    @DisplayName("return a object with a list of musics when music with name exists")
    void findMusicsByMusicNameOrArtistName_shouldReturnListOfMusic_WhenMusicNameIsValid() {
        ObjectDTO objectDTO = musicController.findMusicsByMusicNameOrArtistName("Another brick in the wall").getBody();

        Assertions.assertEquals(1, objectDTO.getData().size());
        Assertions.assertFalse(objectDTO.getData().isEmpty());
        Assertions.assertNotNull(objectDTO);
        Assertions.assertEquals("asdasdaasdasdasd", objectDTO.getData().get(0).getId());
        Assertions.assertEquals("Another brick in the wall", objectDTO.getData().get(0).getName());
        Assertions.assertEquals("182738192738921398123", objectDTO.getData().get(0).getArtist().getId());
        Assertions.assertEquals("pink floyd", objectDTO.getData().get(0).getArtist().getName());
    }

//    @Test
//    @DisplayName("return a object with a list of all musics when succesfull")
//    void findMusicsWithoutParameters_shouldReturnAllMusics() {
//        ObjectDTO objectDTO = musicController.findMusicsByMusicNameOrArtistName().getBody();
//
//        Assertions.assertEquals(1, objectDTO.getData().size());
//        Assertions.assertFalse(objectDTO.getData().isEmpty());
//        Assertions.assertNotNull(objectDTO);
//        Assertions.assertEquals("asdasdaasdasdasd", objectDTO.getData().get(0).getId());
//        Assertions.assertEquals("Another brick in the wall", objectDTO.getData().get(0).getName());
//        Assertions.assertEquals("182738192738921398123", objectDTO.getData().get(0).getArtist().getId());
//        Assertions.assertEquals("pink floyd", objectDTO.getData().get(0).getArtist().getName());
//    }

    @Test
    @DisplayName("Throws InvalidFilterException When filter has less than 3 character")
    void findMusicsByMusicNameOrArtistName_ThrowsInvalidFilterException_WhenFilterIsInvalid() {
        Assertions.assertThrows(InvalidFilterException.class, () -> musicController.findMusicsByMusicNameOrArtistName("pi"));
    }

    @Test
    @DisplayName("Throws EmptyListException When filter is Not Found")
    void ThrowsEmptyListException_WhenFilterIs_NotFound() {
        Assertions.assertThrows(EmptyListException.class, () -> musicController.findMusicsByMusicNameOrArtistName("filtroinvalido"));
    }




}
