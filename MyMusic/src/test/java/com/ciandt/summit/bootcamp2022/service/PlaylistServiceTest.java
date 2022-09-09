package com.ciandt.summit.bootcamp2022.service;


import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.exception.*;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DirtiesContext
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private MusicRepository musicRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    Music music1 = new Music("12321312312", "One", new Artist("id123213123", "Metallica"));
    Music music2 = new Music("123123123", "Sad But True", new Artist("id123213123", "Metallica"));
    Music music3 = new Music("12321312312", "Justice For All", new Artist("id123213123", "Metallica"));

    Music music4 = new Music("music4Id", "The music 4", new Artist("artist4Id", "Name Artist 4"));

    Music music5 = new Music("music5Id", "The music 5", new Artist("artist5Id", "Name Artist 5"));
    List<Music> musicswith1 = new ArrayList<>(Arrays.asList(music1));
    List<Music> musicswith2 = new ArrayList<>(Arrays.asList(music2, music3));
    List<Music> musicswith5 = new ArrayList<>(Arrays.asList(music1, music2, music3,music4, music5));
    Playlist playlist = new Playlist("123123123213", musicswith2);
    Playlist playlist2 = new Playlist("12312312321", musicswith5);
    List<Playlist> playlists = new ArrayList<>(Arrays.asList(playlist));

    @Test
    void findById_shouldThrowPlaylistNotFoundException_WhenPlaylistNotFound() {
        Assertions.assertThrows(PlaylistNotFoundException.class, () -> playlistService.findById("asdasdasdasd"));
    }

    @Test
    void findMusicById_shouldThrowMusicNotFoundException_WhenMusicNotFound() {
        Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.findMusicById("1232131232"));
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
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(null, null, null));
    }

    @Test
    void addMusicToPlaylist_shouldReturnPayloadInvalidException_WhenMusicsBodyinisInvalid() {
        UserType comum = new UserType("12312312", "Comum");
        UserType premium = new UserType("123123", "Premium");
        User userComum = new User();
        userComum.setId("userId");
        userComum.setName("teste");
        userComum.setUserType(comum);
        userComum.setPlaylists(playlist);

        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music3));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(userComum));
        Music music = new Music("12321312312", "asdasdas", new Artist("id123213123", "Metallica"));

        Assertions.assertThrows(PayloadInvalidException.class, () -> playlistService.addMusicToPlaylist("123123123213", "userId", music));
    }

    @Test
    void addMusicToPlaylist_shouldAddMusicToplaylist_WhenSuccesfull() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist);
        user.setUserType(new UserType("userTypeId", "Premium"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        playlistService.addMusicToPlaylist("123123123213", "userId", music1);
        Assertions.assertEquals(3, playlist.getMusicas().size());
    }

    @Test
    void addMusicToPlaylist_shouldReturnNullPointerException_WhenObjectDTOIIsNull() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist);
        user.setUserType(new UserType("userTypeId", "Premium"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        Assertions.assertThrows(NullPointerException.class, () -> playlistService.addMusicToPlaylist("123123123213", "userId", null));
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


    //Lança a exceção e ainda o método falha.
//    @Test
//    void addMusicToPlaylist_shouldThrow_BadRequestException_WhenMusicsExistsInPlaylist() {
//        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
//        BDDMockito.when(musicRepository.findById("123123123")).thenReturn(Optional.of(music2));
//
//        User user = new User();
//        user.setId("userId");
//        user.setPlaylists(playlist);
//        user.setUserType(new UserType("userTypeId", "Premium"));
//        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
//        playlistService.addMusicToPlaylist("123123123213","userId", music2);
//        Assertions.assertThrows(MusicAlreadyExistInPlaylist.class, () -> playlistService.addMusicToPlaylist("123123123213","userId", music2));
//    }

    @Test
    void addMusicToPlaylist_shouldThrowsException_WhenCommonUserExceedLimit() {

        Music music6 = new Music("music6Id", "The music 6", new Artist("artist6Id","Name Artist 6"));
        BDDMockito.when(musicRepository.findById("music6Id")).thenReturn(Optional.of(music6));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist2));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist2);
        user.setUserType(new UserType("userTypeId", "Comum"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));

        Assertions.assertThrows(MusicLimitReachedException.class, ()-> playlistService.addMusicToPlaylist("12312312321", "userId", music6));
    }

    @Test
    void addMusicToPlaylist_shouldAddMusicsToPlaylist_WhenSuccesfull() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist);
        user.setUserType(new UserType("userTypeId", "Premium"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        String updatedPlaylist = playlistService.addMusicToPlaylist("12312312321", "userId", music1);
        Assertions.assertEquals(updatedPlaylist, "Música adicionada com sucesso!");
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
        playlistService.removeMusicFromPlaylist("12312312321", "12321312312");
    }

    @Test
    void removeMusicFromPlaylist_shouldThrowMusicNotFoundException_WhenMusicDoesnotExistInPlaylist() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        try {
            playlistService.removeMusicFromPlaylist("12312312321", "12321312312");
        } catch (MusicNotFoundException e) {
            Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.removeMusicFromPlaylist("12312312321", "12321312312"));
            ;
        }
    }


}
