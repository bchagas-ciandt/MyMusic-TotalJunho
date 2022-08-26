package com.ciandt.summit.bootcamp2022.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistTest {
    Playlist play ;
    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setup() throws IllegalArgumentException {

        play = new Playlist("fdfdfdgtgtrhthrhtrh", new ArrayList<>());
        play = new Playlist();
        play.setId("fdfdfdgtgtrhthrhtrh");
    }

    @Test
    public void playlistInstance(){
        play = new Playlist();
        play.setId("fdfdfdgtgtrhthrhtrh");
        assertEquals("fdfdfdgtgtrhthrhtrh",play.getId());
        assertEquals(false , play.equals("fdfdfdgtgtrhthrhtrh"));


    }

}
