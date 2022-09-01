package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.exception.PayloadInvalidException;
import com.ciandt.summit.bootcamp2022.exception.PlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    @CacheEvict(value = "playlists", allEntries = true)
    public Playlist addMusicToPlaylist(String playlistId, ObjectDTO musics) {
        idValidation(playlistId);

        logger.info("buscando playlist por id");
        Playlist playlist = findById(playlistId);

        payLoadValidation(musics);

        List<Music> playlistMusics = playlist.getMusicas();

        logger.info("adicionando musicas na playlist");
        addMusicToPlaylistIfMusicDoesnotExist(musics.getData(), playlist.getMusicas(), playlist);

        logger.info("Salvando playlist no banco de dados");
        playlistRepository.save(playlist);

        logger.info("retornando playlist com sucesso!");
        return playlistRepository.findById(playlistId).get();
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
        if (id == " " || id == null) {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Id não não pode ser nulo ou branco");
        }
    }

    private void payLoadValidation(ObjectDTO musics) {
        for (Music music : musics.getData()) {
            String id = music.getId();
            Music musicReturn = musicRepository
                    .findById(id)
                    .orElseThrow(() -> new MusicNotFoundException("Música não existe"));
            if (!music.equals(musicReturn)) {
                logger.error("Payload da musica passado de forma incorreta");
                throw new PayloadInvalidException("Payload incorreto: Atributo inválido");
            }
        }
    }

    private void addMusicToPlaylistIfMusicDoesnotExist(List<Music> musics, List<Music> playlistMusics, Playlist playlist) {
        for (Music music : musics) {
            if (!playlistMusics.contains(music)) {
                logger.info("Música adicionada na playlist");
                playlist.getMusicas().add(music);
            } else {
                logger.info("Música já existe na playlist");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Música já existe na playlist");
            }
        }
    }
}
