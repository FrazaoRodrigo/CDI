package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.awt.*;

public class PhasingAlgorithm {

    ComplexImage guess;
    ComplexImage fourierTransformedImage;
    double[][] diffractionPattern;

    public PhasingAlgorithm(double[][] diffractionPattern) {
        diffractionPattern=diffractionPattern;
        guess= new AmplitudeOnlyImage(new double[diffractionPattern.length][diffractionPattern[0].length]);
        fourierTransformedImage=setUpWithRandomPhase();
    }

   public ComplexImage setUpWithRandomPhase() {
        ComplexImage initialImage = new AmplitudeOnlyImage(diffractionPattern);
        initialImage.addRandomPhase();
        return initialImage;
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


    public ComplexImage getGuess() {
        return guess;
    }

}
