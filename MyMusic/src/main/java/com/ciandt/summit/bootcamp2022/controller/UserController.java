package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.version}/users")
@Api(value = "${api.version}/users", tags = "Usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Busca usuário por id")
    @GetMapping(path =  "{userId}")
    public ResponseEntity<User> findUserById(@PathVariable String userId){
        Optional<User> userOptional = userService.findUserById(userId);
        return ResponseEntity.ok(userOptional.get());
    }

    @ApiOperation(value = "Retorna todos os usuários")
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
