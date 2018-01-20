package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.util.concurrent.ExecutionException;

public class ErrorReduction extends PhasingAlgorithm {

    public ErrorReduction(double[][] diffractionPattern) {
        super(diffractionPattern);
        guess = new AmplitudeOnlyImage(new double[diffractionPattern.length][diffractionPattern[0].length]);
        fourierTransformedImage=setUpWithRandomPhase();
    }

    public ErrorReduction(double[][] diffractionPattern,ComplexImage previousGuess) throws ExecutionException, InterruptedException {
        super(previousGuess,diffractionPattern);
        fourierTransformedImage= previousGuess.fft();
    }

    public Double[] iterationWithSupport(int iter, Mask mask) throws ExecutionException, InterruptedException {

        Double[] error = new Double[iter];

        for (int i = 0; i < iter; ++i) {
            fourierTransformedImage = errorReduction(fourierTransformedImage, mask);
            error[i] = errorCalculation(fourierTransformedImage);
            guess=fourierTransformedImage.invfft();
        }

        return error;
    }



    private ComplexImage errorReduction(ComplexImage image, Mask mask) throws ExecutionException, InterruptedException {
        image.setAmplitude(diffractionPattern)
                .invfft()
                .shift()
                .withSupportConstraint(mask)
                .fft()
                .shiftWithPhase();
        return image;
    }
}
