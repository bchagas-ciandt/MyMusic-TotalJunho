package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.*;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import com.ciandt.summit.bootcamp2022.utils.ErrorMassage;
import com.ciandt.summit.bootcamp2022.utils.LogMassage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private UserRepository userRepository;

    @CacheEvict(value = "playlists", allEntries = true)
    public String addMusicToPlaylist(String playlistId, String userId, Music music) {
        idValidation(playlistId);
        idValidation(userId);

        logger.info(LogMassage.FIND_USER_BY_ID);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMassage.USER_NOT_FOUND_EXCEPTION));

        Playlist userPlaylist = user.getPlaylists();

        logger.info(LogMassage.FIND_PLAYLIST_BY_ID);
        Playlist playlist = findById(playlistId);

        isUserPlaylist(userPlaylist, playlist);

        payLoadValidation(music);

        logger.info(LogMassage.ADD_MUSIC_INTO_PLAYLIST);
        addMusicToPlaylistByUserType(user, music);

        logger.info(LogMassage.SAVING_PLAYLIST_INTO_DATA_BASE);
        playlistRepository.save(userPlaylist);

        return "Música adicionada com sucesso!";
    }

    @CacheEvict(value = "playlists", allEntries = true)
    public void removeMusicFromPlaylist(String playlistId, String musicId) {
        idValidation(playlistId);
        idValidation(musicId);

        logger.info(LogMassage.FIND_PLAYLIST_BY_ID);
        Playlist playlist = findById(playlistId);

        logger.info(LogMassage.FINDING_MUSIC_BY_ID);
        Music musicToBeRemoved = findMusicById(musicId);

        logger.info(LogMassage.CHECKING_MUSIC_EXISTS_INTO_PLAYLIST);
        boolean musicExistInPlaylist = playlist.getMusicas().contains(musicToBeRemoved);

        if (musicExistInPlaylist) {
            playlist.getMusicas().remove(musicToBeRemoved);
            playlistRepository.save(playlist);
            logger.info(LogMassage.MUSIC_REMOVED_INTO_PLAYLIST);
        } else {
            logger.error(LogMassage.LOG_ERROR_MUSIC_NOT_FOUND);
            throw new MusicNotFoundException(ErrorMassage.MUSIC_NOT_FOUND_INTO_PLAYLIST);
        }

    }

    public Playlist findById(String id) {
        idValidation(id);
        return playlistRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException(ErrorMassage.PLAYLIST_NOT_FOUND_EXCEPTION));
    }

    public Music findMusicById(String id) {
        idValidation(id);
        return musicRepository.findById(id).orElseThrow(() -> new MusicNotFoundException(ErrorMassage.MUSIC_NOT_FOUND_EXCEPTION));
    }

    @Cacheable("playlists")
    public List<Playlist> findAll() {
        logger.info(LogMassage.FINDING_ALL_PLAYLIST);
        return playlistRepository.findAll();
    }

    private void idValidation(String id) {
        if (Objects.equals(id, " ") || id == null) {
            logger.error(LogMassage.LOG_ERROR_INVALID_ID);
            throw new InvalidIdException(ErrorMassage.INVALID_ID_EXCEPTION);
        }
    }

    private void payLoadValidation(Music music) {
        String id = music.getId();
        Music musicReturn = musicRepository
                .findById(id)
                .orElseThrow(() -> new MusicNotFoundException(ErrorMassage.MUSIC_NOT_FOUND_EXCEPTION));
        if (!music.equals(musicReturn)) {
            logger.error(LogMassage.LOG_ERROR_INVALID_PAYLOAD);
            throw new PayloadInvalidException(ErrorMassage.PAYLOAD_INVALID_EXCEPTION);
        }
    }

    private void addMusicToPlaylistIfMusicDoesnotExist(Music music, List<Music> playlistMusics, Playlist playlist) {
        if (!playlistMusics.contains(music)) {
            logger.info(LogMassage.ADD_MUSIC_INTO_PLAYLIST_SUCCESSFULL);
            playlist.getMusicas().add(music);
        } else {
            logger.info(LogMassage.MUSIC_EXISTS_ON_PLAYLIST);
            throw new MusicAlreadyExistInPlaylistException(ErrorMassage.MUSIC_EXISTS_ON_PLAYLIST_EXCEPTION);
        }
    }

    private void addMusicToPlaylistByUserType(User user, Music music) {
        if (isUserPremium(user)) {
            logger.info(LogMassage.ADD_MUSIC_INTO_PLAYLIST);
            addMusicToPlaylistIfMusicDoesnotExist(music, user.getPlaylists().getMusicas(), user.getPlaylists());
        } else {
            List<Music> userMusics = user.getPlaylists().getMusicas();
            if (userMusics.size() < 5) {
                addMusicToPlaylistIfMusicDoesnotExist(music, user.getPlaylists().getMusicas(), user.getPlaylists());
            } else {
                throw new MusicLimitReachedException(ErrorMassage.MUSIC_LIMIT_REACHED_EXCEPTION);
            }
        }
    }

    private boolean isUserPremium(User user) {
        String tipoUsuario = user.getUserType().getDescription();
        return tipoUsuario.equalsIgnoreCase("premium");
    }

    private void isUserPlaylist(Playlist p1, Playlist p2) {
        if (!p1.getId().equals(p2.getId())) {
            throw new PlaylistNotFoundException(ErrorMassage.PLAYLIST_DOES_NOT_BELONG_TO_USER);
        }
    }

}