package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public List<Music> findMusicsByMusicNameOrArtistName(String filter) {
        List<Music> listToReturn = musicRepository.findByNameArtistOrNameMusic(filter);
        if (filter.length() < 3) {
            throw new InvalidFilterException("Filtro inválido");
        }
        if (listToReturn.isEmpty()) {
            throw new EmptyListException("Não foi encontrada nenhuma música com o filtro: "+ filter);
        }
        return listToReturn;
    }
}
