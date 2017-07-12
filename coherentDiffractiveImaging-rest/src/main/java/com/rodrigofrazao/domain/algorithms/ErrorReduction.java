package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;

public class ErrorReduction extends PhasingAlgorithm {

    public ErrorReduction(double[][] diffractionPattern) {
        super(diffractionPattern);
        guess = new AmplitudeOnlyImage(new double[diffractionPattern.length][diffractionPattern[0].length]);
        fourierTransformedImage=setUpWithRandomPhase();
    }

    public ErrorReduction(double[][] diffractionPattern,ComplexImage previousGuess) {
        super(diffractionPattern);
        guess=previousGuess;
        fourierTransformedImage= previousGuess.fft();
    }

    public Double[] iterationWithSupport(int iter, Mask mask) {

        Double[] error = new Double[iter];

        for (int i = 0; i < iter; ++i) {
            fourierTransformedImage = errorReduction(fourierTransformedImage, mask);
            error[i] = errorCalculation(fourierTransformedImage);
            guess=fourierTransformedImage.invfft();
        }

        return error;
    }



    public ComplexImage errorReduction(ComplexImage image, Mask mask) {
        image.setAmplitude(diffractionPattern)
                .invfft()
                .shift()
                .withSupportConstraint(mask)
                .fft()
                .shiftWithPhase();
        return image;
    }
}
