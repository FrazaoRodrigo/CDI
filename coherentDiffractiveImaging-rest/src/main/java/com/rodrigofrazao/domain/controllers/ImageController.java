package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.*;


@RestController
public class ImageController {
    private ImageService imageService = new ImageService();
    private ComplexImage image;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestBody String body) throws IllegalStateException, IOException {
        BufferedImage bufferedImage = imageService.decodeImageFromFrontEnd(body);
        image = imageService.frontEndImageToComplexImage(bufferedImage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String display() throws IOException {
        return imageService.complexImageToFrontEnd(image);
    }

    @RequestMapping(value = "/fft", method = RequestMethod.GET)
    public String fft() throws IOException {
        image = imageService.fft(image);
        return imageService.complexImageToFrontEnd(image);
    }

}


