package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;

public class ErrorReduction extends PhasingAlgorithm{

    public ErrorReduction() {
    }

    public void ErrorReductionIteration(int iter) {
        fourierTransformedImage = setUp();
        double[] error = new double[iter];

        for(int i = 0; i < iter; ++i) {
            fourierTransformedImage = errorReduction(fourierTransformedImage, mask);
            error[i] = errorCalculation(guess);
        }

    }

    private ComplexImage setUp() {
        ComplexImage initialImage = new AmplitudeOnlyImage(diffractionPattern);
        initialImage.addRandomPhase();
        mask = imageService.getOnesMask(initialImage);
        return errorReduction(initialImage, mask);
    }

    private ComplexImage setUpWithPreviousData(ComplexImage image) {
        mask = imageService.getOnesMask(image);
        return this.errorReduction(image, mask);
    }

    public ComplexImage errorReduction(ComplexImage image,Mask mask) {
        image.setAmplitude(diffractionPattern);
        guess = imageService.invfft(image);
        ComplexImage realPlaneImageWithSupport = imageService.withSupportConstraint(guess, mask);
        return imageService.fft(realPlaneImageWithSupport);
    }
}
