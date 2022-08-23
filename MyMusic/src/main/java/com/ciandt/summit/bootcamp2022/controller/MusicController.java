package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public ResponseEntity findMusicsByMusicNameOrArtistName(@RequestParam(required = false, name = "filtro") String name) {

        List<Music> musics = musicService.findMusicsByMusicNameOrArtistName(name);

        return ResponseEntity.ok(musics);

    }

//    @GetMapping
//    public ResponseEntity get() {
//        return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
//    }

}
