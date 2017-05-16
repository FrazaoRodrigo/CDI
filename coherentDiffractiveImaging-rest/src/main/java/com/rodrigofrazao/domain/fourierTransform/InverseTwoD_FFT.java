package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;

public class InverseTwoD_FFT {
    double[][] inputRealData;
    double[][] inputImaginaryData;
    private int height;
    private int width;
    public double[][] real;
    public double[][] imag;
    public double[][] dataOut;

    public InverseTwoD_FFT setInputData(double[][] inputRealData, double[][] inputImaginaryData) {
        this.inputRealData = inputRealData;
        this.inputImaginaryData = inputImaginaryData;
        return this;
    }

    public InverseTwoD_FFT() {
        this.height = this.inputRealData.length;
        this.width = this.inputRealData[0].length;
        this.dataOut = new double[this.height][this.width];
    }

    public ComplexImage inverseXform2D() {
        int height = this.real.length;
        int width = this.real[0].length;

        for(int ySpace = 0; ySpace < height; ++ySpace) {
            for(int xSpace = 0; xSpace < width; ++xSpace) {
                for(int yWave = 0; yWave < height; ++yWave) {
                    for(int xWave = 0; xWave < width; ++xWave) {
                        this.dataOut[ySpace][xSpace] += (this.real[yWave][xWave] * Math.cos(6.283185307179586D * (1.0D * (double)xSpace * (double)xWave / (double)width + 1.0D * (double)ySpace * (double)yWave / (double)height)) - this.imag[yWave][xWave] * Math.sin(6.283185307179586D * (1.0D * (double)xSpace * (double)xWave / (double)width + 1.0D * (double)ySpace * (double)yWave / (double)height))) / Math.sqrt((double)(width * height));
                    }
                }
            }
        }

        return new ComplexImage(this.dataOut, (double[][])null);
    }
}
