package com.ciandt.summit.bootcamp2022.service;


import com.ciandt.summit.bootcamp2022.entity.*;
import com.ciandt.summit.bootcamp2022.exception.*;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
@DirtiesContext
class PlaylistServiceTest {

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
    @DisplayName("should throw PlaylistNotFoundException when playlist is not found")
    void findById_shouldThrowPlaylistNotFoundException_WhenPlaylistNotFound() {
        Assertions.assertThrows(PlaylistNotFoundException.class, () -> playlistService.findById("asdasdasdasd"));
    }

    @Test
    @DisplayName("should throw MusicNotFoundException when music is not found")
    void findMusicById_shouldThrowMusicNotFoundException_WhenMusicNotFound() {
        Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.findMusicById("1232131232"));
    }

    @Test
    @DisplayName("should throw MusicNotFoundException when music is not found")
    void findMusicById_shouldReturnMusic_WhenSuccessful() {
        BDDMockito.when(musicRepository.findById("123123123")).thenReturn(Optional.of(music2));
        String id = "123123123";
        Music music = musicRepository.findById("123123123").get();
        Assertions.assertNotNull(music);
        Assertions.assertEquals(id, music.getId());
    }

    @Test
    @DisplayName("should throw InvalidIdException when playlist id is null")
    void addMusicToPlaylist_shouldReturnInvalidIdException_WhenPlaylistIdIsNull() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(null, null, null));
    }

    @Test
    @DisplayName("should throw InvalidIdException when playlist id is empty")
    void addMusicToPlaylist_shouldReturnInvalidIdException_WhenPlaylistIdIsEmpty() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.addMusicToPlaylist(" ", null, null));
    }

    @Test
    @DisplayName("should throw PayloadInvalidException when music body is invalid")
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
    @DisplayName("Should add music to playlist when succesfull")
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
    @DisplayName("Should return NullPointerException when objectDTO is null")
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
    @DisplayName("Should return all playlist when succesfull")
    void findAll_ShoulReturnAllPlaylist() {
        BDDMockito.when(playlistRepository.findAll()).thenReturn(playlists);
        Assertions.assertEquals(1, playlistService.findAll().size());
    }

    @Test
    @DisplayName("Should return a by id playlist when succesfull")
    void findById_shouldPlaylist_WhenSuccessful() {
        BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
        String id = "123123123213";
        Playlist playlist1 = playlistService.findById("123123123213");
        Assertions.assertEquals("123123123213", playlist1.getId());
        Assertions.assertNotNull(playlist1);
        Assertions.assertEquals(id, playlist1.getId());
    }



    @Test
    @DisplayName("Should throw MusicAlreadyExistInPlaylist when music already exist in playlist")
    void addMusicToPlaylist_shouldThrow_MusicAlreadyExistInPlaylist_WhenMusicsExistsInPlaylist() throws MusicAlreadyExistInPlaylist {
        try{
            BDDMockito.when(playlistRepository.findById("123123123213")).thenReturn(Optional.of(playlist));
            BDDMockito.when(musicRepository.findById("123123123")).thenReturn(Optional.of(music2));

            User user = new User();
            user.setId("userId");
            user.setPlaylists(playlist);
            user.setUserType(new UserType("userTypeId", "Premium"));
            BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
            playlistService.addMusicToPlaylist("123123123213","userId", music2);

        }catch (MusicAlreadyExistInPlaylist e){
            Assertions.assertThrows(MusicAlreadyExistInPlaylist.class, () -> playlistService.addMusicToPlaylist("123123123213","userId", music2));
        }

    }

    @Test
    @DisplayName("Should throw MusicLimitReachedException when User exced musics limit")
    void addMusicToPlaylist_shouldThrowsMusicLimitReachedException_WhenCommonUserExceedLimit() {

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
    @DisplayName("Should add musics to playlist when succesfull")
    void addMusicToPlaylist_shouldAddMusicsToPlaylist_WhenSuccesfull() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist);
        user.setUserType(new UserType("userTypeId", "Premium"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        String updatedPlaylist = playlistService.addMusicToPlaylist("12312312321", "userId", music1);
        Assertions.assertEquals("Música adicionada com sucesso!", updatedPlaylist);
    }

    @Test
    @DisplayName("Should throw InvalidIdException when playlist id is invalid")
    void removeMusicFromPlaylist_shouldThrowInvalidIdException_WhenPlaylistidIsInvalid() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.removeMusicFromPlaylist(null, "123123123"));
    }

    @Test
    @DisplayName("Should throw invalidIdException when music is is invalid")
    void removeMusicFromPlaylist_shouldThrowInvalidIdException_WhenMusicidIsInvalid() {
        Assertions.assertThrows(InvalidIdException.class, () -> playlistService.removeMusicFromPlaylist("123123123213", null));
    }

    @Test
    @DisplayName("Should remove music if music exist in playlist")
    void removeMusicFromPlaylist_shouldRemoveMusic_IfMusicExistInPlaylist() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist2));
        playlistService.removeMusicFromPlaylist("12312312321", "12321312312");
    }

    @Test
    @DisplayName("Should throw MusicNotFoundException when music")
    void removeMusicFromPlaylist_shouldThrowMusicNotFoundException_WhenMusicDoesnotExistInPlaylist() {
        BDDMockito.when(musicRepository.findById("12321312312")).thenReturn(Optional.of(music1));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        try {
            playlistService.removeMusicFromPlaylist("12312312321", "12321312312");
        } catch (MusicNotFoundException e) {
            Assertions.assertThrows(MusicNotFoundException.class, () -> playlistService.removeMusicFromPlaylist("12312312321", "12321312312"));
        }
    }

    @Test
    @DisplayName("Should throw PlaylistNotFoundException when playlist is not from user")
    void addMusicToPlaylist_shouldThrowsPlaylistNotFoundException_WhenPlaylistIsNotUserPlaylist() {
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist2);
        user.setUserType(new UserType("userTypeId", "Premium"));
        Music music = new Music("12321312312", "asdasdas", new Artist("id123213123", "Metallica"));
        BDDMockito.when(playlistRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(playlist));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));
        BDDMockito.when(musicRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(music));
        Assertions.assertThrows(PlaylistNotFoundException.class, () -> playlistService.addMusicToPlaylist("12312312321", "userId", music));
    }

    @Test
    @DisplayName("Should add music when common user has less than 5 musics in playlist")
    void addMusicToPlaylist_shouldAddMusicToPlaylist_WhenCommonUserHasLessThan5Musics() {

        Music music6 = new Music("music4Id", "The music 4", new Artist("artist4Id","Name Artist 4"));
        BDDMockito.when(musicRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(music6));
        BDDMockito.when(playlistRepository.findById("12312312321")).thenReturn(Optional.of(playlist));
        User user = new User();
        user.setId("userId");
        user.setPlaylists(playlist);
        user.setUserType(new UserType("userTypeId", "Comum"));
        BDDMockito.when(userRepository.findById("userId")).thenReturn(Optional.of(user));

        Assertions.assertEquals("Música adicionada com sucesso!", playlistService.addMusicToPlaylist("12312312321", "userId", music6));
    }


}
