package com.ciandt.summit.bootcamp2022.service;


import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.PlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService ;
    @Mock
    private MusicService musicService;
    Playlist playlist ;

    @BeforeEach
    @SuppressWarnings("unchecked")

    public void setup() throws IllegalArgumentException {


        playlist = new Playlist("fdfdfdgtgtrhthrhtrh", new ArrayList<>());

        playlist.setId("fdfdfdgtgtrhthrhtrh");


    }


    @Test
    public void assertThrowsErrorWhenIDIsNull(){
        String id = "ffsdfdsfsdfdfdsfd";
        var body = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO musics = musicService.findMusicsByMusicNameOrArtistName("cold");
        body.getData().addAll(newMusic.getData());
        Exception error = Assertions.assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(null,body));
        Assertions.assertEquals("Id não não pode ser nulo ou branco", error.getMessage());
    }


    @Test
    //ID INVALIDO
    public void assertThrowsErrorWhenPlaylistNotExists(){
        String id = "ffsdfdsfsdfdfdsfd";
        var body = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO musics = musicService.findMusicsByMusicNameOrArtistName("cold");
        body.getData().addAll(newMusic.getData());
        Exception error = Assertions.assertThrows(PlaylistNotFoundException.class, () -> playlistService.addMusicToPlaylist(id,body));
        Assertions.assertEquals("Playlist com esse ID não existe", error.getMessage());
    }


    /*@Test
    // id valido
    public void assertThrowsErrorWhenIDIsNull2(){

        Music music = new Music("7ff43fef-2d9f-4842-a23a-4be8b35bf422", "The Beatles", new Artist("7ff43fef-2d9f-4842-a23a-4be8b35bf422", "Ifvdfvdfv"));
        Playlist play = new Playlist();
        play.setMusicas((List.of(music)));
        String id = play.getId() ;
        var body = new ObjectDTO(new ArrayList<Music>());
        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>());
        ObjectDTO musics = musicService.findMusicsByMusicNameOrArtistName("cold");
        body.getData().addAll(newMusic.getData());
        Exception error = Assertions.assertThrows(PayloadInvalidException.class, () -> playlistService.addMusicToPlaylist(id,body));
        Assertions.assertEquals("JSON Body incorreto: consulte documentação", error.getMessage());
    }*/

    @Test
    public void payLoadValidation(){

        var body = new ObjectDTO(new ArrayList<Music>());
        body.getData().add(new Music("52fe17fd-6303-43c3-b1c4-e95210f0b9c6","Era Um Garoto Que Como Eu Amava Os Beatles E Os Rolling Stones",new Artist("87ed4cf4-414b-447f-9cc2-d6a08470ff20","Engenheiros do Hawaii")));


    }


}