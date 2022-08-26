package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MusicControllerTest {

    private Music music ;
    @InjectMocks
    private MusicController musicController ;
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MusicService musicService ;

    @BeforeEach
    public void setup() {
        music = new Music("fdfdfdgtgtrhthrhtrh", "The Beatles", new Artist("fdgfdgtthrbrb", "Ifvdfvdfv"));
        music.setId("fdfdfdgtgtrhthrhtrh");

    }


    @Test
    public void searchMusicByName()  {
        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>(Arrays.asList(music)));
        when(musicService.findMusicsByMusicNameOrArtistName(music.getName())).thenReturn(newMusic);
        ObjectDTO musicsByMusicNameOrArtistName = musicController.findMusicsByMusicNameOrArtistName(music.getName()).getBody();
        assertEquals(newMusic,musicsByMusicNameOrArtistName);
    }

    @Test
    public void searchMusicByArtistName()  {
        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>(Arrays.asList(music)));
        when(musicService.findMusicsByMusicNameOrArtistName(music.getArtist().getName())).thenReturn(newMusic);
        ObjectDTO musicsByMusicNameOrArtistName = musicController.findMusicsByMusicNameOrArtistName(music.getArtist().getName()).getBody();
        assertEquals(newMusic,musicsByMusicNameOrArtistName);
    }

    @Test
    public void assertThrowsErrorWhenFilterIsLesserThanThree(){
        Mockito.when(musicService.findMusicsByMusicNameOrArtistName("aa")).thenThrow(InvalidFilterException.class);
        assertThrows(InvalidFilterException.class, () -> musicController.findMusicsByMusicNameOrArtistName("aa"));
    }


    @Test
    public void assertThrowsErrorWhenArtistOrMusicDontExist(){
        Mockito.when(musicService.findMusicsByMusicNameOrArtistName("cdcsffweererer")).thenThrow(Mockito.mock(EmptyListException.class));
        assertThrows(EmptyListException.class, () -> musicController.findMusicsByMusicNameOrArtistName("cdcsffweererer"));
    }




}
