package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class Controller {
    ImageService imageService = new ImageService();

    @RequestMapping("/ro")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    
    @RequestMapping(value = "/fft", method = RequestMethod.POST,consumes = "multipart/form-data")
    public MultipartFile fft(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        ComplexImage image = imageService.getUploadedImageToComplexImage(convFile);
        imageService.fft(image);
        return imageService.convertImageToFrontEnd(image,file.getName(),file.getOriginalFilename(),file.getContentType(),file.getSize());
    }
}
