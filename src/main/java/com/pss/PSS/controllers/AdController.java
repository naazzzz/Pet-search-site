package com.pss.PSS.controllers;


import com.pss.PSS.models.AdEntity;
import com.pss.PSS.models.enums.Status;
import com.pss.PSS.service.AdServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
@Slf4j
public class AdController {

    @Autowired
    AdServiceImpl service;

    @Value("${global.upload.path}")
    private String globaluploadPath;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/{id}")
    public ResponseEntity<AdEntity> getAd(@PathVariable int id){
        AdEntity adsEntity = service.getAd(id).getBody();
        log.info(" GET объявление с id: "+id+" {}", adsEntity);
        return ResponseEntity.ok(adsEntity);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AdEntity> postCreate(@RequestBody AdEntity entity) throws IOException {
        log.info("POST Save ad in db ");
        return new ResponseEntity<>(service.saveAd(entity), HttpStatus.OK);
    }

    @PostMapping("/uploadfile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if(file != null){
              File uploadDir = new File(uploadPath);
               if (!uploadDir.exists()){
                   uploadDir.mkdir();
               }

                String uuidFile = UUID.randomUUID().toString();
               String resultFilename= uuidFile + "." + file.getOriginalFilename();

               file.transferTo(new File(globaluploadPath+uploadPath+resultFilename));
               return uploadPath+resultFilename;
        }

        return uploadPath;
    }

    @PostMapping("/allActive")
    public ResponseEntity<List<AdEntity>> getAllActive(@RequestParam("status") Status status){

        return new ResponseEntity<>(service.getAllActive(status), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<AdEntity>> findAll(){

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<AdEntity> activateAd(@RequestBody AdEntity entity) throws IOException {
        log.info("POST update status ACTIVE ad in db ");
        return new ResponseEntity<>(service.updateStatus(entity), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    @Transactional
    public HttpStatus deleteAd(@RequestBody AdEntity adEntity) throws IOException {
        log.info("POST update status ACTIVE ad in db ");
        service.deleteById(adEntity.getId());
        return  HttpStatus.OK;
    }

}
