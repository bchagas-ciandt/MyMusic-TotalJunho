package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping(path = "{playlistId}/musicas")
    public ResponseEntity<Playlist> addMusicsToPlaylist(@PathVariable String playlistId, @RequestBody ObjectDTO musics) {
        Playlist playlist = playlistService.addMusicToPlaylist(playlistId, musics);
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping (path = "{playlistId}/musicas/{musicId}")
    public ResponseEntity<Void> removeMusicFromPlaylist(@PathVariable String playlistId, @PathVariable String musicId) {
        playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "{playlistId}")
    public ResponseEntity<Playlist> findPlaylistById(@PathVariable String playlistId) {
        return ResponseEntity.ok(playlistService.findById(playlistId));
    }

    @GetMapping(path = "findAll")
    public ResponseEntity<List<Playlist>> findAll() {
        return ResponseEntity.ok(playlistService.findAll());
    }
}
