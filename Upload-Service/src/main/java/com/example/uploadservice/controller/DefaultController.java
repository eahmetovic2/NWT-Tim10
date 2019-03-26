package com.example.uploadservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.uploadservice.model.Zadaca;
import com.example.uploadservice.service.ZadacaService;

import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.service.PredmetService;

import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.service.UcenikService;

import com.example.uploadservice.model.BodoviZadaca;
import com.example.uploadservice.service.BodoviZadacaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
public class DefaultController {

    @Autowired
    private BodoviZadacaService bodoviZadacaService;

	@Autowired
    private PredmetService predmetService;

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private ZadacaService zadacaService;


    @RequestMapping(value="/testAllModels", method = RequestMethod.GET)
    public void testAllModels() { 
        Ucenik ucenik = new Ucenik("John","Doe");
        ucenikService.save(ucenik);
        Predmet predmet = new Predmet("IM2");
        predmetService.save(predmet);
        Zadaca zadaca = new Zadaca("open",predmet,"fn","fi","ft","wbl","wvl");
        zadacaService.save(zadaca);
        BodoviZadaca bodoviZadaca = new BodoviZadaca(zadaca,ucenik,3);
        bodoviZadacaService.save(bodoviZadaca); 
    }

    @RequestMapping(value="/getAllBodoviZadaca", method = RequestMethod.GET)
    public List<BodoviZadaca> getAllBodoviZadaca() { 
        return bodoviZadacaService.getAllBodoviZadaca();
    }

    @RequestMapping(value="/getPredmet", method = RequestMethod.GET)
    public Predmet getPredmet() { 
        return zadacaService.getZadacaById(2).get().getPredmet();
    }

    @RequestMapping(value="/testUploadFile", method = RequestMethod.POST)
    public BodoviZadaca uploadSingleFile(@RequestParam("file") MultipartFile multipart) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        Path tempFile = Files.createTempFile(multipart.getOriginalFilename().split("\\.")[0] , multipart.getContentType().split("/")[1]);
        Files.write(tempFile, multipart.getBytes());
        File file = tempFile.toFile();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));
      

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:3000/upload/";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> response = restTemplate.postForObject(serverUrl, requestEntity, Map.class);
        String fileName = multipart.getOriginalFilename().split("\\.")[0];
        String fileType = multipart.getContentType();
        String fileId = response.get("id");
        String webContentLink = response.get("webContentLink");
        String webViewLink = response.get("webViewLink");

        Ucenik ucenik = new Ucenik("Upload","Doe");
        ucenikService.save(ucenik);
        Predmet predmet = new Predmet("IM3");
        predmetService.save(predmet);
        Zadaca zadaca = new Zadaca("open",predmet,fileName,fileId,fileType,webContentLink,webViewLink);
        zadacaService.save(zadaca);
        BodoviZadaca bodoviZadaca = new BodoviZadaca(zadaca,ucenik,5);
        return bodoviZadacaService.save(bodoviZadaca); 
    }
    
}