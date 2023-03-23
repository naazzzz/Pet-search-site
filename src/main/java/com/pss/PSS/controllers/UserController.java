package com.pss.PSS.controllers;

import com.pss.PSS.model.UserEntity;
import com.pss.PSS.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @GetMapping("/{login}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String login){
        UserEntity userEntity = service.getUser(login).getBody();
        log.info(" get Авторизация пользователя "+login, userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/{login}")
    public boolean postUser(@PathVariable String login, @RequestParam String password) {
        log.info(" Проверка пользователя ");
        return service.checkUser(login, password);
    }

    @PostMapping(value = "/registration")
            public ResponseEntity<UserEntity> postReg(@RequestBody UserEntity entity){
            log.info(" Save user in db ");
            return new ResponseEntity<>(service.saveUser(entity), HttpStatus.OK);
        }

    }


