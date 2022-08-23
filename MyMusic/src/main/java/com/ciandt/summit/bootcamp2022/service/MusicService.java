package com.ciandt.summit.bootcamp2022.service;

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

@Service
public class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);

    @Autowired
    private MusicRepository musicRepository;

    public List<Music> findMusicsByMusicNameOrArtistName(String filter) {
        if (filter.length() < 3) {
            logger.error("Filtro com menos de 3 caracteres");
            throw new InvalidFilterException("Filtro com menos de 3 caracteres");
        }

        List<Music> listToReturn = musicRepository.findByNameArtistOrNameMusic(filter);
        logger.info("Buscando músicas com o filtro: "+ filter);

        if (listToReturn.isEmpty()) {
            logger.error("Lista vazia para o filtro do parametro");
            throw new EmptyListException("Não foi encontrada nenhuma música com o filtro: "+ filter);
        }
        return listToReturn;
    }
}
