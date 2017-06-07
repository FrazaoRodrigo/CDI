package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.util.Arrays;

public class AmplitudeOnlyImage extends ComplexImage {

    public AmplitudeOnlyImage(double[][] amplitude) {
        this.amplitude=amplitude;
        this.height = amplitude.length;
        this.width = amplitude[0].length;
        this.phase = new double[height][width];
    }

    public ComplexImage withSupportConstraint(Mask mask) {
        return applyRealSpaceSupport(mask.getMask());
    }

    private ComplexImage applyRealSpaceSupport(boolean[][] mask) {
        double[][] array = new double[height][width];

        for(int j = 0; j < height; ++j) {
            for(int k = 0; k < width; ++k) {
                int myInt = mask[j][k]?1:0;
                array[j][k] = amplitude[j][k] * (double)myInt;
            }
        }

        return this;
    }
}
