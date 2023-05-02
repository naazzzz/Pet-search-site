package com.pss.PSS.controllers;

import com.pss.PSS.models.User;
import com.pss.PSS.models.UserDescriptionEntity;
import com.pss.PSS.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/addDescriptionToUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> addDescrToUser(@RequestBody UserDescriptionEntity ud){
        log.info(" Save user description in db and save user");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user= service.getUser(username);
        UserDescriptionEntity userDescriptionEntity= service.saveUserDesc(ud);
        user.setUserDescriptionId(userDescriptionEntity.getId());
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    @PostMapping(value = "/getDescription")
    public ResponseEntity<UserDescriptionEntity> getDesc(@RequestBody User user){
        log.info(" Get descr form user" + user.getUsername());
        User user_now=service.getUser(user.getUsername(),user.getId());
        return new ResponseEntity<>(service.findDescById(user_now.getUserDescriptionId()), HttpStatus.OK);
    }
}
