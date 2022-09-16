package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.utils.ErrorMassage;
import com.ciandt.summit.bootcamp2022.utils.LogMassage;
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

    @Cacheable("musicswithfilter")
    public ObjectDTO findMusicsByMusicNameOrArtistName(String filter) {
        if (filter.length() < 3) {
            logger.error(LogMassage.LOG_ERROR_INVALID_FILTER);
            throw new InvalidFilterException(ErrorMassage.INVALID_FILTER_EXCEPTION);
        }

        logger.info(LogMassage.FINDING_MUSIC_BY_FILTER);
        List<Music> musics = musicRepository.findByNameArtistOrNameMusic(filter);

        emptyPlaylistShouldThrowException(musics);

        ObjectDTO objectDTO = ObjectDTO.builder().data(musics).build();

        logger.info(LogMassage.RETURNING_MUSIC_BY_FILTER);
        return objectDTO;
    }

    @Cacheable("allmusics")
    public ObjectDTO findMusicsWithoutParameters() {
        logger.info(LogMassage.FINDING_ALL_MUSICS);
        List<Music> musics = musicRepository.findAll();
        emptyPlaylistShouldThrowException(musics);
        List<Music> listMusicsSorted = musics.stream().sorted((a1, a2) -> a1.getArtist().getName()
                .compareTo(a2.getArtist().getName())).collect(Collectors.toList());
        return ObjectDTO.builder().data(listMusicsSorted).build();
    }

    private void emptyPlaylistShouldThrowException(List<Music> musics) {
        if (musics.isEmpty()) {
            logger.error(LogMassage.LOG_ERROR_EMPTY_LIST);
            throw new EmptyListException(ErrorMassage.EMPTY_LIST_EXCEPTION);
        }
    }
}
