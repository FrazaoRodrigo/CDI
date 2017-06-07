package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;

public class InverseTwoD_FFT {


    public static ComplexImage inverseXform2D(double[][] inputRealData, double[][] inputImaginaryData) {

        int height = inputRealData.length;
        int width = inputRealData[0].length;
        double[][] dataOut = new double[height][width];

        for(int ySpace = 0; ySpace < height; ++ySpace) {
            for(int xSpace = 0; xSpace < width; ++xSpace) {
                for(int yWave = 0; yWave < height; ++yWave) {
                    for(int xWave = 0; xWave < width; ++xWave) {
                        dataOut[ySpace][xSpace] += (inputRealData[yWave][xWave] * Math.cos(6.283185307179586D * (1.0D * (double)xSpace * (double)xWave / (double)width + 1.0D * (double)ySpace * (double)yWave / (double)height)) - inputImaginaryData[yWave][xWave] * Math.sin(6.283185307179586D * (1.0D * (double)xSpace * (double)xWave / (double)width + 1.0D * (double)ySpace * (double)yWave / (double)height))) / Math.sqrt((double)(width * height));
                    }
                }
            }
        }

        return new AmplitudeOnlyImage(dataOut);
    }
}
