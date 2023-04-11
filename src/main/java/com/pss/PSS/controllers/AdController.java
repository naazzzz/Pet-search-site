package com.pss.PSS.controllers;

import com.pss.PSS.model.AdEntity;
import com.pss.PSS.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MulticastSocket;
import java.util.UUID;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
@Slf4j
public class AdController {

    private final AdService service;


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

               file.transferTo(new File(uploadPath+resultFilename));
               return uploadPath+resultFilename;
        }

        return uploadPath;
    }


}
