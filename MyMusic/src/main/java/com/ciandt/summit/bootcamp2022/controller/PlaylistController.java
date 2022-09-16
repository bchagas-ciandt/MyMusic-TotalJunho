package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/playlists")
@Api(value = "api/v1/playlists", tags = "Playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @ApiOperation(value = "Adiciona músicas na playlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Música adicionada com sucesso!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Não Autorizado")
    })
    @PostMapping(path = "{playlistId}/{userId}/musics")
    public ResponseEntity<String> addMusicsToPlaylist(@PathVariable String playlistId,@PathVariable String userId ,@RequestBody Music music) {
        String addMusicToPlaylist = playlistService.addMusicToPlaylist(playlistId,userId, music);
        return ResponseEntity.ok(addMusicToPlaylist);
    }

    @ApiOperation(value = "remove música da playlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Música removida com sucesso!"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Não Autorizado")
    })
    @PutMapping (path = "{playlistId}/musics/{musicId}")
    public ResponseEntity<Void> removeMusicFromPlaylist(@PathVariable String playlistId, @PathVariable String musicId) {
        playlistService.removeMusicFromPlaylist(playlistId, musicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Busca playlist por id")
    @GetMapping(path = "{playlistId}")
    public ResponseEntity<Playlist> findPlaylistById(@PathVariable String playlistId) {
        return ResponseEntity.ok(playlistService.findById(playlistId));
    }

    @ApiOperation(value = "Retorna todas as playlists")
    @GetMapping
    public ResponseEntity<List<Playlist>> findAll() {
        return ResponseEntity.ok(playlistService.findAll());
    }
}
