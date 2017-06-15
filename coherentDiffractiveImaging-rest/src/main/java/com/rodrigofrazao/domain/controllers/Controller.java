package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
public class Controller {
    ImageService imageService = new ImageService();
    ComplexImage image;

    @RequestMapping("/ro")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST,consumes = "multipart/form-data")
    public void upload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        byte [] byteArr=file.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        image = imageService.frontEndImageToComplexImage(inputStream);
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String display() throws IOException {
        return imageService.convertComplexImageToFrontEnd(image);
    }

    @RequestMapping(value = "/fft", method = RequestMethod.GET)
    public String fft() throws IOException {
        image=imageService.fft(image);
        return imageService.convertComplexImageToFrontEnd(image);
    }
}
