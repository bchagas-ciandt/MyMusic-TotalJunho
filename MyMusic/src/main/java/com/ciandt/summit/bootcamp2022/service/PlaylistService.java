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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    public Playlist addMusicToPlaylist(String playlistId, ObjectDTO musics) {
        if (playlistId.equals(null) || playlistId == " ") {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Deve ser passado um ID válido");
        }
        if (musics == null) {
            throw new PayloadInvalidException("JSON Body incorreto: consulte documentação");
        }

        logger.info("buscando playlist por id");
        Optional<Playlist> playlist = Optional.ofNullable(playlistRepository
                .findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist com esse ID não existe")));

        payLoadValidation(musics);

        List<Music> playlistMusics = playlist.get().getMusicas();

        logger.info("adicionando musicas na playlist");
        for (Music music : musics.getData()) {
            if (!playlistMusics.contains(music)) {
                logger.info("Música adicionada na playlist");
                playlist.get().getMusicas().add(music);
            } else {
                logger.info("Música já existe na playlost");
            }
        }

        logger.info("Salvando playlist no banco de dados");
        playlistRepository.save(playlist.get());

        logger.info("retornando playlist com sucesso!");
        return playlistRepository.findById(playlistId).get();
    }

    public Playlist findById(String id) {
        if (id.equals(null) || id == " ") {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Deve ser passado um ID válido");
        }
        return playlistRepository.findById(id).get();
    }

    private void payLoadValidation(ObjectDTO musics) {

        for (Music music : musics.getData()) {
            Music musicReturn = musicRepository
                    .findById(music.getId())
                    .orElseThrow(() -> new PayloadInvalidException("Payload incorreto: consultar documentação"));
            if (!musicRepository.existsById(music.getId())) {
                throw new MusicNotFoundException("Música não existe");
            }
            if (!music.getId().equals(musicReturn.getId()))
            if (!music.getName().equals(musicReturn.getName())) {
                logger.error("nome da música passado de forma incorreta");
                throw new PayloadInvalidException("Payload incorreto: nome da música passado de forma incorreta");
            }
            if (!music.getArtist().getId().equals(musicReturn.getArtist().getId())) {
                logger.error("id do artista passado de forma incorreta");
                throw new PayloadInvalidException("Payload incorreto: id do artista passado de forma incorreta");
            }
            if (!music.getArtist().getName().equals(musicReturn.getArtist().getName())) {
                logger.error("nome do artista passado de forma incorreta");
                throw new PayloadInvalidException("Payload incorreto: nome do artista passado de forma incorreta");
            }
        }
    }
}
