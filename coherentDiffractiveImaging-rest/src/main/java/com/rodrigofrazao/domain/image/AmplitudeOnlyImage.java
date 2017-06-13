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


}
