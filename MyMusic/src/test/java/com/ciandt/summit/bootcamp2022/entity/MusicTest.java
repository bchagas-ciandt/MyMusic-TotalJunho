package com.ciandt.summit.bootcamp2022.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class MusicTest {

    Music music ;
    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setup() throws IllegalArgumentException {

        music = new Music("fdfdfdgtgtrhthrhtrh", "The Beatles", new Artist("fdgfdgtthrbrb", "Ifvdfvdfv"));
        music = new Music();
        music.setId("fdfdfdgtgtrhthrhtrh");

        music.setName("The Beatles");

    }

    @Test
    public void musicInstance(){
        assertEquals("fdfdfdgtgtrhthrhtrh",music.getId());
        assertEquals("The Beatles",music.getName());
        assertEquals(false , music.equals("fdfdfdgtgtrhthrhtrh"));


    }
}