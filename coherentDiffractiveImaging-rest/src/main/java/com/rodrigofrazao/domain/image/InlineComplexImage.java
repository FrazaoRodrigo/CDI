package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.PolarComplex;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InlineComplexImage {

    private int height, width;
    public double[] amplitude, phase;

    public InlineComplexImage(int height, int width, double[] amplitude) {
        this.height = height;
        this.width = width;
        this.amplitude = amplitude;
        this.phase = new double[width * height];
    }

    public InlineComplexImage(int height, int width, double[] amplitude, double[] phase) {
        this.height = height;
        this.width = width;
        this.amplitude = amplitude;
        this.phase = phase;
    }

    public ComplexImage toComplexImage() {
        double[][] outAmplitude = new double[height][width];
        double[][] outphase = new double[height][width];


        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[j][k] = amplitude[k + j * width];
                outphase[j][k] = phase[k + j * width];
            }
        }
        return new ComplexImage(outAmplitude, outphase);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double[] getAmplitude() {
        return amplitude;
    }

    public double[] getPhase() {
        return phase;
    }

    public double[] re() {
        double[] realArray = new double[height * width];
        for (int j = 0; j < height * width; j++) {
            PolarComplex polarComplex = new PolarComplex(amplitude[j], phase[j]);
            realArray[j] = polarComplex.re();
        }
        return realArray;
    }

    public double[] im() {
        double[] imagArray = new double[height * width];
        for (int j = 0; j < height * width; j++) {
            PolarComplex polarComplex = new PolarComplex(amplitude[j], phase[j]);
            imagArray[j] = polarComplex.im();
        }
        return imagArray;
    }


}
