package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.DTO.ObjectDTO;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/musics")
@Api(value = "api/v1/musics", tags = "Músicas")
public class MusicController {

    @Autowired
    private MusicService musicService;


    @ApiOperation(value = "Retorna uma lista de música de acordo com o filtro passado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista retornada com sucesso!"),
            @ApiResponse(code = 204, message = "Lista de música vazia"),
            @ApiResponse(code = 400, message = "Filtro com menos de 2 caracteres"),
            @ApiResponse(code = 401, message = "Não Autorizado")
    })
    @GetMapping
    public ResponseEntity<ObjectDTO> findMusicsByMusicNameOrArtistName(@RequestParam(required = false, name = "filtro") String name) {
        ObjectDTO objectDTO;
        if (name.isEmpty()) {
            objectDTO = musicService.findMusicsWithoutParameters();
        } else {
            objectDTO = musicService.findMusicsByMusicNameOrArtistName(name);
        }

        return ResponseEntity.ok(objectDTO);

    }
}
