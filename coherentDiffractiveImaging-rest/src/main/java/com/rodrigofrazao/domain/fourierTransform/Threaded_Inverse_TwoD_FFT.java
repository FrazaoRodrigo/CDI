package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.concurrent.Callable;

public class Threaded_Inverse_TwoD_FFT implements Callable {

    double[][] inputRealData,inputImaginaryData;
    private int startIdx, nThreads;

    public Threaded_Inverse_TwoD_FFT(ComplexImage inputData, int startIdx, int nThreads) {
        this.inputImaginaryData = inputData.im();
        this.inputRealData=inputData.re();
        this.startIdx = startIdx;
        this.nThreads = nThreads;
    }

    @Override
    public ComplexImage call() throws Exception {
        int height = inputRealData.length;
        int width = inputRealData[0].length;
        double[][] dataOut = new double[height][width];

        for (int ySpace = this.startIdx; ySpace < height; ySpace += this.nThreads) {
            for (int xSpace = 0; xSpace < width; ++xSpace) {
                for (int yWave = 0; yWave < height; ++yWave) {
                    for (int xWave = 0; xWave < width; ++xWave) {
                        dataOut[ySpace][xSpace] += (inputRealData[yWave][xWave] * Math.cos(6.283185307179586D * (1.0D * (double) xSpace * (double) xWave / (double) width + 1.0D * (double) ySpace * (double) yWave / (double) height)) - inputImaginaryData[yWave][xWave] * Math.sin(6.283185307179586D * (1.0D * (double) xSpace * (double) xWave / (double) width + 1.0D * (double) ySpace * (double) yWave / (double) height))) / Math.sqrt((double) (width * height));
                    }
                }
            }
        }

        return new AmplitudeOnlyImage(dataOut);
    }
}

