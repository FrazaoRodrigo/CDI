package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;

public class TwoD_FFT {
    double[][] inputData;
    private int height;
    private int width;
    private double[][] realOut;
    private double[][] imagOut;
    private double[][] amplitudeOut;
    private double[][] phaseOut;

    public TwoD_FFT() {
        this.height = this.inputData.length;
        this.width = this.inputData[0].length;
        this.phaseOut = new double[this.height][this.width];
    }

    public TwoD_FFT setInputData(double[][] inputData) {
        this.inputData = inputData;
        return this;
    }

    public ComplexImage twoDfft() {
        for(int yWave = 0; yWave < this.height; ++yWave) {
            for(int xWave = 0; xWave < this.width; ++xWave) {
                for(int ySpace = 0; ySpace < this.height; ++ySpace) {
                    for(int xSpace = 0; xSpace < this.width; ++xSpace) {
                        this.realOut[yWave][xWave] += this.inputData[ySpace][xSpace] * Math.cos(6.283185307179586D * (1.0D * (double)xWave * (double)xSpace / (double)this.width + 1.0D * (double)yWave * (double)ySpace / (double)this.height)) / Math.sqrt((double)(this.width * this.height));
                        this.imagOut[yWave][xWave] -= this.inputData[ySpace][xSpace] * Math.sin(6.283185307179586D * (1.0D * (double)xWave * (double)xSpace / (double)this.width + 1.0D * (double)yWave * (double)ySpace / (double)this.height)) / Math.sqrt((double)(this.width * this.height));
                        this.amplitudeOut[yWave][xWave] = Math.sqrt(this.realOut[yWave][xWave] * this.realOut[yWave][xWave] + this.imagOut[yWave][xWave] * this.imagOut[yWave][xWave]);
                        this.phaseOut[yWave][xWave] = Math.atan2(this.imagOut[yWave][xWave] * this.imagOut[yWave][xWave], this.realOut[yWave][xWave] * this.realOut[yWave][xWave]);
                    }
                }
            }
        }

        return new ComplexImage(this.amplitudeOut, this.phaseOut);
    }
}
