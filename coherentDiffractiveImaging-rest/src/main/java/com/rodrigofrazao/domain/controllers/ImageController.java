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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("imageString") String imageString) throws IllegalStateException, IOException {
        BufferedImage bufferedImage = imageService.decodeImageFromFrontEnd(imageString);
        image = imageService.frontEndImageToComplexImage(bufferedImage);
    }

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


