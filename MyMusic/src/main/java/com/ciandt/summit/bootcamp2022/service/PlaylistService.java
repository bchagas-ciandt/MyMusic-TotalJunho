package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.PlaylistReqBody;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
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

    public Playlist addMusicToPlaylist(String playlistId, PlaylistReqBody musics) {
        if (playlistId.equals(null) || playlistId == " ") {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Deve ser passado um ID válido");
        }

        logger.info("buscando playlist por id");
        Optional<Playlist> playlist = Optional.ofNullable(playlistRepository.findById(playlistId).orElseThrow(() -> new InvalidIdException("Playlist com esse ID não existe")));

        for (Music music : musics.getData()) {
            if (!musicRepository.existsById(music.getId())) {
                logger.error("musica não existe, lançando exceção");
                throw new MusicNotFoundException("Música não existe");
            }
        }
        logger.info("musicas adicionada na playlist");
        playlist.get().getMusicas().addAll(musics.getData());

        logger.info("retornando playlist com sucesso!");
        return playlist.get();
    }
}
