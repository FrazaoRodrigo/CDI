package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;


@RestController
public class Controller {
    ImageService imageService = new ImageService();
    ComplexImage image;
    BufferedImage test;

    @RequestMapping("/ro")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void upload(@RequestParam("imageString") String imageString) throws IllegalStateException, IOException {

        String base64Image = imageString.split(",")[0];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        test = bufferedImage;
        image = imageService.frontEndImageToComplexImage(bufferedImage);
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String display() throws IOException {
        return imageService.convertComplexImageToFrontEnd(image);
    }

    @RequestMapping(value = "/fft", method = RequestMethod.GET)
    public String fft() throws IOException {
        image = imageService.fft(image);
        return imageService.convertComplexImageToFrontEnd(image);
    }

    @RequestMapping(value = "/displaytest", method = RequestMethod.GET)
    public String displaytest() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.flush();
        ImageIO.write(test,"png",baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}


