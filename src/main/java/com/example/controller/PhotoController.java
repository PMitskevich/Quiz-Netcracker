package com.example.controller;

import com.example.model.Photo;
import com.example.service.interfaces.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PhotoController {
    private final AmazonClient amazonClient;

    @Autowired
    public PhotoController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public Photo uploadFile(@RequestPart(value = "file") MultipartFile file) {

        return new Photo(this.amazonClient.uploadFile(file));
    }
}
