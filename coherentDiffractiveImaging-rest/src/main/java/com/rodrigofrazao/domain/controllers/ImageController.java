package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.Fftshift.shiftOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {
    private ImageService imageService = new ImageService();
    private ComplexImage image,fourierImage;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestBody String body) throws IllegalStateException, IOException {
        BufferedImage bufferedImage = imageService.decodeImageFromFrontEnd(body);
        image = imageService.frontEndImageToComplexImage(bufferedImage);
    }


    @RequestMapping(value = "/display", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse display() throws IOException {
        return new ImageStringResponse(imageService.complexImageToFrontEnd(image));
    }

    @RequestMapping(value = "/fft", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse fft() throws IOException {
        try {
            image=image.checkImageDimensions();
            fourierImage = imageService.fft(image);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ImageStringResponse(imageService.complexImageToFrontEnd(fourierImage));
    }

    @RequestMapping(value = "/fftshift", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse shift() throws IOException {

        ComplexImage result = fourierImage.shiftWithPhase();
        return new ImageStringResponse(imageService.complexImageToFrontEnd(result));
    }

    @RequestMapping(value = "/fft_Log", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse fft_log_scaled() throws IOException {

        ComplexImage result = imageService.logAndScaleFFT(fourierImage);
        return new ImageStringResponse(imageService.complexImageToFrontEnd(result));
    }

    @RequestMapping(value = "/inv_fft", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse inv_fft() throws IOException, ExecutionException, InterruptedException {
        ComplexImage result = imageService.invfft(fourierImage);
        return new ImageStringResponse(imageService.complexImageToFrontEnd(result));
    }

    @RequestMapping(value = "/low_pass", method = RequestMethod.GET,produces = "application/json")
    public ImageStringResponse lowpassfilter() throws IOException, ExecutionException, InterruptedException {

        ComplexImage result = imageService.lowpassfilter(fourierImage.shiftWithPhase(),10);
        result = imageService.invfft(result);
        return new ImageStringResponse(imageService.complexImageToFrontEnd(result));
    }

}


