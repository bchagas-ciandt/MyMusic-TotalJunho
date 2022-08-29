package com.ciandt.summit.bootcamp2022.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistTest {

    Artist art;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setup() throws IllegalArgumentException {

        art = new Artist("fdgfdgtthrbrb", "Ifvdfvdfv");

        art.setId("fdfdfdgtgtrhthrhtrh");

        art.setName("The Beatles");

    }

    @Test
    public void artistInstance(){
        assertEquals("fdfdfdgtgtrhthrhtrh",art.getId());
        assertEquals(false , art.equals("fdfdfdgtgtrhthrhtrh"));
    }


}