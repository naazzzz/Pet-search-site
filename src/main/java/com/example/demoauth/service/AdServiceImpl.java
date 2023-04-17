package com.example.demoauth.service;


import com.example.demoauth.models.AdEntity;

import com.example.demoauth.models.enums.Status;
import com.example.demoauth.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl {

    @Autowired
    AdRepository repository;


    public ResponseEntity<AdEntity> getAd(int id){
            AdEntity adEntity = repository.findById(id);
            if(!adEntity.equals(null)){
                return new ResponseEntity<>(adEntity, HttpStatus.NOT_FOUND);
            }else {
                return ResponseEntity.ok(adEntity);
            }
    }

    public AdEntity saveAd(AdEntity entity){
        entity.setStatus(Status.INACTIVE);
        return repository.save(entity);
    }
}
