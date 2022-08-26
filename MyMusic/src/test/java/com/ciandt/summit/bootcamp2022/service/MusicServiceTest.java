package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MusicServiceTest")
public class MusicServiceTest {

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private MusicService musicService ;

    Music music ;


    @BeforeEach
    @SuppressWarnings("unchecked")

    public void setup() throws IllegalArgumentException {
        music = new Music("fdfdfdgtgtrhthrhtrh", "The Beatles", new Artist("fdgfdgtthrbrb", "Ifvdfvdfv"));
        music.setId("fdfdfdgtgtrhthrhtrh");
        music.setName("The Beatles");
        music.setArtist(new Artist("fdgfdgtthrbrb", "The Beatles"));

    }





    @Test
    @DisplayName("")
    public void assertThrowsErrorWhenFilterLesserThanThree(){
        String filter = "aa";
        Exception error = Assertions.assertThrows(InvalidFilterException.class, () -> musicService.findMusicsByMusicNameOrArtistName(filter));
        Assertions.assertEquals("Filtro com menos de 3 caracteres", error.getMessage());
    }


    @Test
    public void filterNotFound(){
        String filter = "tevdfvfdv";
        Exception error = Assertions.assertThrows(EmptyListException.class, () -> musicService.findMusicsByMusicNameOrArtistName(filter));
        Assertions.assertEquals("Não foi encontrada nenhuma música com o filtro: "+ filter, error.getMessage());
    }


  /*  @Test
    public void findMusicsByMusicNameOrArtistName(){
        String filter = "The Beatles";
        List<Music> musicTest ;

        Mockito.when(musicRepository.findByNameArtistOrNameMusic(filter)).thenReturn(List.of(music));
        musicTest = musicService.findMusicsByMusicNameOrArtistName(filter);
        Assertions.assertTrue(musicTest.contains(music));

    }*/
}