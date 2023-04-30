package com.pss.PSS.service;


import com.pss.PSS.models.AdEntity;

import com.pss.PSS.models.enums.Status;
import com.pss.PSS.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AdEntity> getAllActive(Status status){
        return repository.getAllByStatus(status);
    }
    public List<AdEntity> findAll(){
        return repository.getAllByIdIsNotNull();
    }

    public AdEntity updateStatus(AdEntity entity){
        entity.setStatus(Status.ACTIVE);
        return repository.save(entity);
    }

    public void deleteById(int id){
        repository.deleteById(id);
    }
}
