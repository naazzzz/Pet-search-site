package com.pss.PSS.service;

import com.pss.PSS.model.UsersEntity;
import com.pss.PSS.repository.UserRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.pss.PSS.model.enums.Role.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService  {
    private final UserRepository repository;

    public UsersEntity getUser(String login){
        return repository.findByUsername(login);
    }

    public boolean checkUser(String login, String pass){
        UsersEntity user = repository.findByUsername(login);
        if(Objects.equals(user.getUsername(), login) && Objects.equals(user.getPass(), pass)){
            log.info(user.getUsername() + "="+login+" |"+user.getPass()+"= "+pass);
            return true;
        }
        log.info(user.getUsername() + "="+login+" |"+user.getPass()+"= "+pass);
        return false;
    }

    public UsersEntity saveUser(UsersEntity entity){
        entity.setRole(USER);
        return repository.save(entity);
    }


}
