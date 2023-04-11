package com.pss.PSS.service;

import com.pss.PSS.model.UserDescriptionEntity;
import com.pss.PSS.model.UserEntity;
import com.pss.PSS.repository.UserDescriptionRepository;
import com.pss.PSS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.pss.PSS.model.enums.Role.USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService  {
    private final UserRepository repository;
    private final UserDescriptionRepository repository_desc;

    public ResponseEntity<UserEntity> getUser(String login){
        UserEntity userEntity = repository.findByUsername(login);
        if(userEntity.getUsername().isEmpty()){
            return new ResponseEntity<>(userEntity, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userEntity);
    }

    public boolean checkUser(String login, String pass){
        UserEntity user = repository.findByUsername(login);
        if(Objects.equals(user.getUsername(), login) && Objects.equals(user.getPass(), pass)){
            log.info(user.getUsername() + "="+login+" |"+user.getPass()+"= "+pass);
            return true;
        }
        log.info(user.getUsername() + "="+login+" |"+user.getPass()+"= "+pass);
        return false;
    }

    public UserEntity saveUser(UserEntity entity){
        entity.setRole(USER);
        return repository.save(entity);
    }

    public UserDescriptionEntity saveUserDesc(UserDescriptionEntity entity){

        if(repository_desc.existsByEmail(entity.getEmail())){
            return repository_desc.findByEmail(entity.getEmail());
        }


        return repository_desc.save(entity);
    }

}
