package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;

public class ErrorReduction extends PhasingAlgorithm{

    public ErrorReduction() {
    }

    public void ErrorReductionIteration(int iter) {
        this.fourierTransformedImage = this.setUp();
        double[] error = new double[iter];

        for(int i = 0; i < iter; ++i) {
            error[i] = this.errorCalculation(this.guess);
            this.fourierTransformedImage = this.errorReduction(this.fourierTransformedImage, this.mask);
        }

    }

    private ComplexImage setUp() {
        ComplexImage initialImage = new ComplexImage();
        initialImage.addRandomPhase();
        this.mask = this.imageService.getOnesMask(initialImage);
        return this.errorReduction(initialImage, this.mask);
    }

    private ComplexImage setUpWithPreviousData(ComplexImage image) {
        this.mask = this.imageService.getMask(image);
        return this.errorReduction(image, this.mask);
    }

    public ComplexImage errorReduction(ComplexImage image, boolean[][] mask) {
        image.setAmplitude(this.diffractionPattern);
        this.guess = this.imageService.invfft(image);
        ComplexImage realPlaneImageWithSupport = this.imageService.withSupportConstraint(this.guess, mask);
        return this.imageService.fft(realPlaneImageWithSupport);
    }
}
