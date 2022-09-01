package com.ciandt.summit.bootcamp2022.service;


import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.exception.PayloadInvalidException;
import com.ciandt.summit.bootcamp2022.exception.PlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private PlaylistService playlistService ;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
        Music music1 = new Music("12321312312", "One", new Artist("id123213123", "Metallica"));
        Music music2 = new Music("123123123", "Sad But True", new Artist("id123213123", "Metallica"));
        Music music3 = new Music("12321312312", "Justice For All", new Artist("id123213123", "Metallica"));
        List<Music> musicswith1 = new ArrayList<>(Arrays.asList(music1));
        List<Music> musicswith2 = new ArrayList<>(Arrays.asList(music2,music3));
        List<Music> musicswith3 = new ArrayList<>(Arrays.asList(music1,music2, music3));
        Playlist playlist = new Playlist("123123123213",  musicswith2);
        Playlist playlist2 = new Playlist("12312312321",  musicswith3);
        List<Playlist> playlists = new ArrayList<>(Arrays.asList(playlist));

    @Test
    void findById_shouldThrowPlaylistNotFoundException_WhenPlaylistNotFound() {
        Assertions.assertThrows(PlaylistNotFoundException.class, () ->  playlistService.findById("asdasdasdasd"));
    }

    @Test
    void findMusicById_shouldThrowMusicNotFoundException_WhenMusicNotFound() {
        Assertions.assertThrows(MusicNotFoundException.class, () ->  playlistService.findMusicById("1232131232"));
    }

    @Test
    void findMusicById_shouldReturnMusic_WhenSuccessful() {
        BDDMockito.when(musicRepository.findById("123123123")).thenReturn(Optional.of(music2));
        String id = "123123123";
        Music music = musicRepository.findById("123123123").get();
        Assertions.assertNotNull(music);
        Assertions.assertEquals(id, music.getId());
    }

    @Test
    void addMusicToPlaylist_shouldReturnInvalidIdException_WhenPlaylistIdIsInvalid() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(null, null));
    }

    @Test
    void addMusicToPlaylist_shouldReturnMusicNotFoundException_WhenMusicsIdInObjectDTOisInvalid() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<Music>());
        objectDTO.getData().add(new Music("adsadasd", "One", new Artist("id123213123", "Metallica")));
        Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.addMusicToPlaylist("123123123213", objectDTO));
    }

    @Test
    void addMusicToPlaylist_shouldReturnPayloadInvalidException_WhenMusicsBodyinObjectDTOisInvalid() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music3));
        Music music = new Music("12321312312", "asdasdas", new Artist("id123213123", "Metallica"));
        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<Music>());
        objectDTO.getData().add(music);
        Assertions.assertThrows(PayloadInvalidException.class, () -> playlistService.addMusicToPlaylist("123123123213", objectDTO));
    }

    @Test
    void addMusicToPlaylist_shouldAddMusicToplaylist_WhenSuccesfull() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<Music>());
        playlistService.addMusicToPlaylist("123123123213", objectDTO);
        Assertions.assertEquals(2, playlist.getMusicas().size());
    }

    @Test
    void addMusicToPlaylist_shouldReturnNullPointerException_WhenObjectDTOIIsNull() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        Assertions.assertThrows(NullPointerException.class, () -> playlistService.addMusicToPlaylist("123123123213", null));
    }

    @Test
    void findAll_ShoulReturnAllPlaylist() {
        BDDMockito.when(playlistRepository.findAll()).thenReturn(playlists);
        Assertions.assertEquals(1, playlistService.findAll().size());
    }

    @Test
    void findById_shouldPlaylist_WhenSuccessful() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        String id = "123123123213";
        Playlist playlist1 = playlistService.findById("123123123213");
        Assertions.assertEquals("123123123213", playlist1.getId());
        Assertions.assertNotNull(playlist1);
        Assertions.assertEquals(id, playlist1.getId());
    }

    @Test
    void addMusicToPlaylist_shouldThrow_BadRequestException_WhenMusicsExistsInPlaylist() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        BDDMockito.when(musicRepository.findById("123123123")).thenReturn(Optional.of(music2));
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music3));
        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<>());
        objectDTO.getData().addAll(playlist.getMusicas());
        Assertions.assertThrows(ResponseStatusException.class, () -> playlistService.addMusicToPlaylist("123123123213", objectDTO));
    }

//    @Test
//    void addMusicToPlaylist_shouldAddMusicToPlaylist_WhenMusicDoesnotExistInPlaylist() {
//
//    }

    @Test
    void addMusicToPlaylist_shouldAddMusicsToPlaylist_WhenSuccesfull() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        ObjectDTO objectDTO = new ObjectDTO(new ArrayList<>(musicswith1));
        Playlist playlist1 = playlistService.addMusicToPlaylist("12312312321", objectDTO);
    }

    @Test
    void removeMusicFromPlaylist_shouldThrowInvalidIdException_WhenPlaylistidIsInvalid() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.removeMusicFromPlaylist(null, "123123123"));
    }

    @Test
    void removeMusicFromPlaylist_shouldThrowInvalidIdException_WhenMusicidIsInvalid() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.removeMusicFromPlaylist("123123123213", null));
    }

    @Test
    void removeMusicFromPlaylist_shouldThrow_MusicNotFoundException_WhenMusicsDoesnotExistsInPlaylist() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.removeMusicFromPlaylist("123123123213", "12321312312"));
    }

    @Test
    void removeMusicFromPlaylist_shouldRemoveMusic_IfMusicExistInPlaylist() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist2));
        playlistService.removeMusicFromPlaylist("12312312321","12321312312");
    }

    @Test
    void removeMusicFromPlaylist_shouldThrowMusicNotFoundException_WhenMusicDoesnotExistInPlaylist() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        try {
            playlistService.removeMusicFromPlaylist("12312312321", "12321312312");
        } catch (MusicNotFoundException e) {
            Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.removeMusicFromPlaylist("12312312321","12321312312"));;
        }
    }


}
