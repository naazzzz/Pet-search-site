package com.pss.PSS.controllers;

import com.pss.PSS.model.AdEntity;
import com.pss.PSS.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
@Slf4j
public class AdController {

    private final AdService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdEntity> getAd(@PathVariable int id){
        AdEntity adsEntity = service.getAd(id).getBody();
        log.info(" GET объявление с id: "+id+" {}", adsEntity);
        return ResponseEntity.ok(adsEntity);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AdEntity> postCreate(@RequestBody AdEntity entity){
        log.info("POST Save ad in db ");
        return new ResponseEntity<>(service.saveAd(entity), HttpStatus.OK);
    }


}
