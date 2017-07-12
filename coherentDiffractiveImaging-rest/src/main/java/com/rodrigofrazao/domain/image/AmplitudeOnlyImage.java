package com.rodrigofrazao.domain.image;


public class AmplitudeOnlyImage extends ComplexImage {

    public AmplitudeOnlyImage(double[][] amplitude) {
        this.amplitude=amplitude;
        this.height = amplitude.length;
        this.width = amplitude[0].length;
        this.phase = new double[height][width];
    }


}
