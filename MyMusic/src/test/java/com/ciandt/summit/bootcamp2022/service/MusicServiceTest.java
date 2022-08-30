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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
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
    private MusicService musicService;

    Music music;
    ObjectDTO objectDTO;

    @BeforeEach
    @SuppressWarnings("unchecked")

    public void setup() throws IllegalArgumentException {
        music = new Music("fdfdfdgtgtrhthrhtrh", "The Beatles", new Artist("fdgfdgtthrbrb", "Ifvdfvdfv"));
        music.setId("fdfdfdgtgtrhthrhtrh");
        music.setName("The Beatles");
        music.setArtist(new Artist("fdgfdgtthrbrb", "The Beatles"));

    }

    @Test
    @DisplayName("deve estourar InvalidFilterException quando filtro tiver menos que 3 catacteres")
    public void assertThrowsErrorWhenFilterLesserThanThree() {
        String filter = "aa";
        Exception error = Assertions.assertThrows(InvalidFilterException.class, () -> musicService.findMusicsByMusicNameOrArtistName(filter));
        assertEquals("Filtro com menos de 3 caracteres", error.getMessage());
    }

    @Test
    public void filterNotFound() {
        String filter = "tevdfvfdv";
        Exception error = Assertions.assertThrows(EmptyListException.class, () -> musicService.findMusicsByMusicNameOrArtistName(filter));
        assertEquals("Não foi encontrada nenhuma música com o filtro: " + filter, error.getMessage());
    }

}