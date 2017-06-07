package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;

public class TwoD_FFT {


    public static ComplexImage twoDfft(double[][] inputData) {
        int height = inputData.length;
        int width = inputData[0].length;
        double[][] realOut=new double[height][width];
        double[][] imagOut=new double[height][width];
        double[][] amplitudeOut=new double[height][width];
        double[][] phaseOut=new double[height][width];


        for(int yWave = 0; yWave < height; ++yWave) {
            for(int xWave = 0; xWave < width; ++xWave) {
                for(int ySpace = 0; ySpace < height; ++ySpace) {
                    for(int xSpace = 0; xSpace < width; ++xSpace) {
                        realOut[yWave][xWave] += inputData[ySpace][xSpace] * Math.cos(2*Math.PI * (1.0D * (double)xWave * (double)xSpace / (double) width + 1.0D * (double)yWave * (double)ySpace / (double)height)) / Math.sqrt((double) width * height);
                        imagOut[yWave][xWave] -= inputData[ySpace][xSpace] * Math.sin(2*Math.PI * (1.0D * (double)xWave * (double)xSpace / (double) width + 1.0D * (double)yWave * (double)ySpace / (double)height)) / Math.sqrt((double) width * height);
                        amplitudeOut[yWave][xWave] = Math.sqrt(realOut[yWave][xWave] * realOut[yWave][xWave] +imagOut[yWave][xWave] * imagOut[yWave][xWave]);
                        phaseOut[yWave][xWave] = Math.atan2(imagOut[yWave][xWave] * imagOut[yWave][xWave], realOut[yWave][xWave] * realOut[yWave][xWave]);
                    }
                }
            }
        }

        return new ComplexImage(amplitudeOut, phaseOut);
    }
}
