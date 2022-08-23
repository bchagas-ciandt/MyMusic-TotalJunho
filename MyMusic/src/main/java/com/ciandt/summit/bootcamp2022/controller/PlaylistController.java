package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.PlaylistReqBody;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping(path = "{playlistId}/musicas")
    public ResponseEntity<Playlist> addMusicsToPlaylist(@PathVariable String playlistId, @RequestBody PlaylistReqBody musics) {
        Playlist playlist = playlistService.addMusicToPlaylist(playlistId, musics);
        return ResponseEntity.ok(playlist);
    }
}
