package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;

import java.awt.*;

public class PhasingAlgorithm {
    ImageService imageService;
    ComplexImage guess;
    ComplexImage fourierTransformedImage;
    boolean[][] mask;
    double[][] diffractionPattern;

    public PhasingAlgorithm() {
    }

    public double errorCalculation(ComplexImage image) {
        double[][] img = image.getAmplitude();
        double error = 0.0D;

        for(int j = 0; j < image.getWidth(); ++j) {
            for(int k = 0; k < image.getHeight(); ++k) {
                error += Math.pow(img[j][k] - this.diffractionPattern[j][k], 2.0D);
            }
        }

        return error;
    }

    public Image reconstructedImage() {
        return this.imageService.guessToImageDisplay(this.guess);
    }

}
