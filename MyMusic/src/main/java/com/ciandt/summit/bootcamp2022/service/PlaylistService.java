package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.*;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
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

        logger.info("Buscando usuario por id");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usúario não existe"));

        Playlist userPlaylist = user.getPlaylists();

        logger.info("Buscando playlist por id");
        Playlist playlist = findById(playlistId);

        isUserPlaylist(userPlaylist, playlist);

        payLoadValidation(music);

        logger.info("adicionando musicas na playlist");
        addMusicToPlaylistByUserType(user, music);

        logger.info("Salvando playlist no banco de dados");
        playlistRepository.save(userPlaylist);

        return "Música adicionada com sucesso!";
    }

    @CacheEvict(value = "playlists", allEntries = true)
    public void removeMusicFromPlaylist(String playlistId, String musicId) {
        idValidation(playlistId);
        idValidation(musicId);

        logger.info("buscando playlist por id");
        Playlist playlist = findById(playlistId);

        logger.info("buscando música por id");
        Music musicToBeRemoved = findMusicById(musicId);

        logger.info("verificando se música existe na playlist");
        boolean musicExistInPlaylist = playlist.getMusicas().contains(musicToBeRemoved);

        if (musicExistInPlaylist) {
            playlist.getMusicas().remove(musicToBeRemoved);
            playlistRepository.save(playlist);
            logger.info("Música removida da playlist");
        } else {
            logger.error("Música não existe na playlist, lançando exceção");
            throw new MusicNotFoundException("Música não existe na playlist");
        }

    }

    public Playlist findById(String id) {
        idValidation(id);
        return playlistRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException("Playlist com esse ID não existe"));
    }

    public Music findMusicById(String id) {
        idValidation(id);
        return musicRepository.findById(id).orElseThrow(() -> new MusicNotFoundException("Música com esse id não existe"));
    }

    @Cacheable("playlists")
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    private void idValidation(String id) {
        if (Objects.equals(id, " ") || id == null) {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Id não não pode ser nulo ou branco");
        }
    }

    private void payLoadValidation(Music music) {
        String id = music.getId();
        Music musicReturn = musicRepository
                .findById(id)
                .orElseThrow(() -> new MusicNotFoundException("Música não existe"));
        if (!music.equals(musicReturn)) {
            logger.error("Payload da musica passado de forma incorreta");
            throw new PayloadInvalidException("Payload incorreto: Atributo inválido");
        }
    }

    private void addMusicToPlaylistIfMusicDoesnotExist(Music music, List<Music> playlistMusics, Playlist playlist) {
        if (!playlistMusics.contains(music)) {
            logger.info("Música adicionada na playlist");
            playlist.getMusicas().add(music);
        } else {
            logger.info("Música já existe na playlist");
            throw new MusicAlreadyExistInPlaylistException("Música já existe na playlist");
        }
    }

    private void addMusicToPlaylistByUserType(User user, Music music) {
        if (isUserPremium(user)) {
            logger.info("adicionando musica na playlist");
            addMusicToPlaylistIfMusicDoesnotExist(music, user.getPlaylists().getMusicas(), user.getPlaylists());
        } else {
            List<Music> userMusics = user.getPlaylists().getMusicas();
            if (userMusics.size() < 5) {
                addMusicToPlaylistIfMusicDoesnotExist(music, user.getPlaylists().getMusicas(), user.getPlaylists());
            } else {
                throw new MusicLimitReachedException("Você atingiu o número máximo de músicas em sua playlist. Para adicionar mais músicas contrate o plano Premium.");
            }
        }
    }

    private boolean isUserPremium(User user) {
        String tipoUsuario = user.getUserType().getDescription();
        return tipoUsuario.equalsIgnoreCase("premium");
    }

    private void isUserPlaylist(Playlist p1, Playlist p2) {
        if (!p1.getId().equals(p2.getId())) {
            throw new PlaylistNotFoundException("Não existe essa playlist no perfil do usuário");
        }
    }

}