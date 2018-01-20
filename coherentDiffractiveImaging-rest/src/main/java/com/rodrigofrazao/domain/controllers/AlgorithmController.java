package com.rodrigofrazao.domain.controllers;

import com.rodrigofrazao.domain.algorithms.AlgorithmService;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.image.BufferedImage;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200/CDI")
@Controller
public class AlgorithmController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private AlgorithmService algorithmService;

    private double[][] diffractionPattern;

    @RequestMapping(value = "/uploadDP", method = RequestMethod.POST)
    public void singleErrorReduction(@RequestBody String diffractionPatternFrontEnd) throws IOException {
        BufferedImage bufferedImage = imageService.decodeImageFromFrontEnd(diffractionPatternFrontEnd);
        ComplexImage diffractionPatternImage = imageService.frontEndImageToComplexImage(bufferedImage);
        diffractionPattern = diffractionPatternImage.getAmplitude();

        algorithmService.errorReduction_multiPhaseIteration(diffractionPattern,1)

    }

}
