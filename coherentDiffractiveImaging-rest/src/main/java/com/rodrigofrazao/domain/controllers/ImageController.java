package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {
    private ImageService imageService = new ImageService();
    private ComplexImage image;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestBody String body) throws IllegalStateException, IOException {
        BufferedImage bufferedImage = imageService.decodeImageFromFrontEnd(body);
        image = imageService.frontEndImageToComplexImage(bufferedImage);
    }


    @RequestMapping(value = "/display", method = RequestMethod.GET,produces = "application/json")
    public StringResponse display() throws IOException {
        return new  StringResponse(imageService.complexImageToFrontEnd(image));
    }

    @RequestMapping(value = "/fft", method = RequestMethod.GET,produces = "application/json")
    public StringResponse fft() throws IOException {
        image = imageService.fft(image);
        return new StringResponse(imageService.complexImageToFrontEnd(image));
    }

}


