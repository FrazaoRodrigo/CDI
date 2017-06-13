package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;

public class HIO extends PhasingAlgorithm {


    public HIO(double[][] diffractionPattern, ComplexImage previousGuess) {
        super(diffractionPattern);
        guess = previousGuess;
        fourierTransformedImage = previousGuess.fft();
    }

    public Double[] iterationWithSupport(int iter, double beta, Mask mask) {

        Double[] error = new Double[iter];

        for (int i = 0; i < iter; ++i) {
            fourierTransformedImage = hio(fourierTransformedImage, mask, beta);
            error[i] = errorCalculation(fourierTransformedImage);
            guess = fourierTransformedImage.invfft();
        }

        return error;
    }

    public ComplexImage hio(ComplexImage image, Mask mask, double beta) {
        image.setAmplitude(diffractionPattern)
                .invfft();
        image = hybridAlgorithm(image, mask, beta);
        return image.fft();
    }

    public ComplexImage hybridAlgorithm(ComplexImage image, Mask mask, double beta) {

        double[][] array = new double[image.getHeight()][image.getWidth()];
        double[][] outArray = new double[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getHeight(); ++j) {
            for (int k = 0; k < image.getWidth(); ++k) {
                array[j][k] = guess.getAmplitude()[j][k] - (image.getAmplitude()[j][k] * beta);
            }
        }

        ComplexImage intermediate = new AmplitudeOnlyImage(array);
        intermediate.withInverseSupportConstraint(mask);
        image.withSupportConstraint(mask);

        for (int j = 0; j < image.getHeight(); ++j) {
            for (int k = 0; k < image.getWidth(); ++k) {
                outArray[j][k] = image.getAmplitude()[j][k] + intermediate.getAmplitude()[j][k];
            }
        }
        return new AmplitudeOnlyImage(outArray);
    }
}
