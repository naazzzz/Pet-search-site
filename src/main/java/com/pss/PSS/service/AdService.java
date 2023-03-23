package com.pss.PSS.service;

import com.pss.PSS.model.AdEntity;
import com.pss.PSS.model.UserEntity;
import com.pss.PSS.model.enums.Status;
import com.pss.PSS.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.pss.PSS.model.enums.Role.USER;
@Service
@RequiredArgsConstructor
@Slf4j
public class AdService {

    private final AdRepository repository;


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
