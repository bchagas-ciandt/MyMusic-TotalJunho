package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.PlaylistReqBody;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService ;

    Playlist playlist ;

    @BeforeEach
    @SuppressWarnings("unchecked")

    public void setup() throws IllegalArgumentException {


        playlist = new Playlist("fdfdfdgtgtrhthrhtrh", new ArrayList<>());

        playlist.setId("fdfdfdgtgtrhthrhtrh");


    }


    @Test
    public void assertThrowsErrorWhenFilterLesserThanThree(){
        String id = null;
        var body = new PlaylistReqBody(new ArrayList<Music>());
        body.getData().add(new Music("52fe17fd-6303-43c3-b1c4-e95210f0b9c6","Era Um Garoto Que Como Eu Amava Os Beatles E Os Rolling Stones",new Artist("87ed4cf4-414b-447f-9cc2-d6a08470ff20","Engenheiros do Hawaii")));
        Exception error = Assertions.assertThrows(NullPointerException.class, () -> playlistService.addMusicToPlaylist(id,body));
        Assertions.assertEquals("Deve ser passado um ID válido", error.getMessage());

    }
    
    @Test
    public void payLoadValidation(){

        var body = new PlaylistReqBody(new ArrayList<Music>());
        body.getData().add(new Music("52fe17fd-6303-43c3-b1c4-e95210f0b9c6","Era Um Garoto Que Como Eu Amava Os Beatles E Os Rolling Stones",new Artist("87ed4cf4-414b-447f-9cc2-d6a08470ff20","Engenheiros do Hawaii")));


    }


}
