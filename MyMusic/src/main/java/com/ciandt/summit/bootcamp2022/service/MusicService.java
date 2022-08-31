package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);

    @Autowired
    private MusicRepository musicRepository;

    @Cacheable("musics")
    public ObjectDTO findMusicsByMusicNameOrArtistName(String filter) {
        if (filter.length() < 3) {
            logger.error("Filtro com menos de 3 caracteres");
            throw new InvalidFilterException("Filtro com menos de 3 caracteres");
        }

        logger.info("Buscando músicas com o filtro: "+ filter);
        List<Music> musics = musicRepository.findByNameArtistOrNameMusic(filter);

        emptyPlaylistShouldThrowException(musics);

        ObjectDTO objectDTO = ObjectDTO.builder().data(musics).build();

        logger.info("Retornando músicas com o filtro: "+ filter);
        return objectDTO;
    }
    public ObjectDTO findMusicsWithoutParameters(){
        logger.info("Buscando músicas sem filtro");
        List<Music> musics = musicRepository.findAll();
        emptyPlaylistShouldThrowException(musics);
        List<Music> listMusicsSorted = musics.stream().sorted((a1, a2)->a1.getArtist().getName()
                .compareTo(a2.getArtist().getName())).collect(Collectors.toList());
        ObjectDTO objectDTO = ObjectDTO.builder().data(listMusicsSorted).build();

        return objectDTO;
    }
    private void emptyPlaylistShouldThrowException(List<Music> musics){
        if (musics.isEmpty()) {
            logger.error("Lista vazia para o filtro do parametro");
            throw new EmptyListException("Não foi encontrada nenhuma música para esta busca.");
        }
    }
}
