package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.awt.*;

public class PhasingAlgorithm {
    ImageService imageService;
    ComplexImage guess;
    ComplexImage fourierTransformedImage;
    Mask mask;
    double[][] diffractionPattern;

    public PhasingAlgorithm() {
    }

    public double errorCalculation(ComplexImage image) {
        double[][] img = image.getAmplitude();
        double error = 0.0D;

        for(int j = 0; j < img.length; ++j) {
            for(int k = 0; k < img[0].length; ++k) {
                error += Math.pow(img[j][k] - diffractionPattern[j][k], 2.0D);
            }
        }

        return error;
    }

    public Image reconstructedImage() {
        return imageService.guessToImageDisplay(guess);
    }

    public ComplexImage getGuess() {
        return guess;
    }

    public Mask getMask() {
        return mask;
    }
}
