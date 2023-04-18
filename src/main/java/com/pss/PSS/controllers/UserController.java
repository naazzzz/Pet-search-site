package com.pss.PSS.controllers;

import com.pss.PSS.models.UserDescriptionEntity;
import com.pss.PSS.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    UserDetailsServiceImpl service;

    @PostMapping(value = "/description")
    public ResponseEntity<UserDescriptionEntity> postCreateDescr(@RequestBody UserDescriptionEntity ud){
        log.info(" Save user description in db ");
        return new ResponseEntity<>(service.saveUserDesc(ud), HttpStatus.OK);
    }
}
