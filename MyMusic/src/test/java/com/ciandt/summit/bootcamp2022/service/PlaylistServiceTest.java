package com.ciandt.summit.bootcamp2022.service;


import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.exception.PlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private PlaylistService playlistService;
    @Mock
    private MusicService musicService;
    Playlist playlistEmpty;
    Playlist playlistWithOneMusic;

    Playlist playlistUpdated;
    Artist artist;
    Music music;

    @BeforeEach
    @SuppressWarnings("unchecked")

    public void setup() throws IllegalArgumentException {

        playlistEmpty = new Playlist("idPlaylistEmpty", new ArrayList<>());
        playlistEmpty.setId("idPlaylistEmpty");
        artist = new Artist("idArtist", "The Artist");
        music = new Music("idMusic", "The Music", artist);
        playlistWithOneMusic = new Playlist("idPlaylist", new ArrayList<>());
        playlistWithOneMusic.getMusicas().add(music);

    }

    @Test
    public void assertThrowsErrorWhenIDIsNull() {

        var body = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO musics = musicService.findMusicsByMusicNameOrArtistName("cold");
        body.getData().addAll(newMusic.getData());
        Exception error = assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(null, body));
        assertEquals("Id não pode ser nulo ou branco", error.getMessage());
    }

    @Test
    public void assertThrowsErrorWhenPlaylistDoesNotExists() {
        String id = "idPlaylistEmpty";
        var body = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO newMusic = new ObjectDTO(new ArrayList<Music>());

        ObjectDTO musics = musicService.findMusicsByMusicNameOrArtistName("cold");
        body.getData().addAll(newMusic.getData());
        Exception error = assertThrows(PlaylistNotFoundException.class, () -> playlistService.addMusicToPlaylist(id, body));
        assertEquals("Playlist com esse ID não existe", error.getMessage());
    }

    @Test
    public void payLoadValidation() {

        var body = new ObjectDTO(new ArrayList<Music>());
        body.getData().add(new Music("52fe17fd-6303-43c3-b1c4-e95210f0b9c6", "Era Um Garoto Que Como Eu Amava Os Beatles E Os Rolling Stones", new Artist("87ed4cf4-414b-447f-9cc2-d6a08470ff20", "Engenheiros do Hawaii")));

    }

    @Test
    public void shouldReturnAllPlaylist() {
        List<Playlist> playlists = new ArrayList<>(List.of(playlistEmpty));

        when(playlistRepository.findAll()).thenReturn(playlists);

        List<Playlist> playlistsDTO = playlistService.findAll();

        assertNotNull(playlistsDTO);
        assertEquals(playlists.size(), playlistsDTO.size());
        assertEquals("idPlaylistEmpty", playlistsDTO.get(0).getId());

    }

    @Test
    public void shouldReturnEmptyList() {
        when(playlistRepository.findAll()).thenReturn(Collections.emptyList());

        List<Playlist> playlistsDTO = playlistService.findAll();

        assertThat(playlistsDTO).isEmpty();
    }

    @Test
    public void shouldReturnPlaylistById() {
        String id = "idPlaylistEmpty";

        when(playlistRepository.findById(id)).thenReturn(Optional.ofNullable(playlistEmpty));

        Playlist playlistReturned = playlistService.findById(id);

        assertNotNull(playlistReturned);
        assertEquals("idPlaylistEmpty", playlistReturned.getId());
    }

    @Test
    public void shouldRemoveAMusicFromThePlaylist() throws Exception {
        String playlistId = "idPlaylist";
        String musicId = "idMusic";

        when(playlistRepository.findById(playlistId)).thenReturn(Optional.ofNullable(playlistWithOneMusic));
        when(musicRepository.findById(musicId)).thenReturn(Optional.ofNullable(music));

        playlistService.removeMusicFromPlaylist(playlistId, musicId);

        assertEquals("idPlaylist", playlistWithOneMusic.getId());
        assertEquals(0, playlistWithOneMusic.getMusicas().size());
    }
}