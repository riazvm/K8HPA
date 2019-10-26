package com.hpa.sample.imageresize.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageResizerController {

    private final ImageResizerService imageResizerService;

    public ImageResizerController(ImageResizerService imageResizerService) {
        this.imageResizerService = imageResizerService;
    }

    @PostMapping("/resize")
    public String resize(
            @RequestParam("data")MultipartFile file) {

        try {
            BufferedImage resizedImage = this.imageResizerService.resize(file.getBytes());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write( resizedImage  , "jpg", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
             
            return "OK";
            		
            		//ResponseEntity.ok()
                    //.contentLength(bytes.length)
                    //.contentType(MediaType.APPLICATION_OCTET_STREAM)
                    //.body(bytes);
        } catch (IOException e) {
            return "Failed";
        }

    }

@RequestMapping("/health")
public String getHealth() throws InterruptedException {
    return "Ok";
    }

}
