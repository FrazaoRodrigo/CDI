package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.InlineComplexImage;

import java.util.concurrent.Callable;

public class Threaded_Inline_TwoD_FFT implements Callable {

    private int startIdx, nThreads;

    private InlineComplexImage inputData;
    public double[][] amplitudeOut;
    public double[][] phaseOut;

    public Threaded_Inline_TwoD_FFT(int startIdx, int nThreads, InlineComplexImage inputData) {
        this.startIdx = startIdx;
        this.nThreads = nThreads;
        this.inputData = inputData;
    }

    @Override
    public Object call() throws Exception {

        int width = inputData.getWidth();
        int height = inputData.getHeight();
        double[] realOut=new double[height*width];
        double[] imagOut=new double[height*width];
        amplitudeOut= new double[height][width];
        phaseOut = new double[height][width];

        for(int yWave = this.startIdx; yWave < height; yWave+=this.nThreads) {
            for(int xWave = 0; xWave < width; ++xWave) {
             //   for(int ySpace = 0; ySpace < height; ++ySpace) {
               //     for(int xSpace = 0; xSpace < width; ++xSpace) {xSpace+ySpace*width
                        for (int i = 0; i<width*height; ++i){
                        int ySpace=(i/width);
                        int xSpace=i-width*(i/width);
                        realOut[yWave*width+xWave] += inputData.getAmplitude()[i] * Math.cos(2*Math.PI * (1.0D * (double)xWave * (double)xSpace / (double) width + 1.0D * (double)yWave * (double)ySpace / (double)height)) / Math.sqrt((double) width * height);
                        imagOut[yWave*width+xWave] -= inputData.getAmplitude()[i] * Math.sin(2*Math.PI * (1.0D * (double)xWave * (double)xSpace / (double) width + 1.0D * (double)yWave * (double)ySpace / (double)height)) / Math.sqrt((double) width * height);
                        amplitudeOut[yWave][xWave] = Math.sqrt(realOut[yWave*width+xWave] * realOut[yWave*width+xWave] +imagOut[yWave*width+xWave] * imagOut[yWave*width+xWave]);
                        phaseOut[yWave][xWave] = Math.atan2(imagOut[yWave*width+xWave] * imagOut[yWave*width+xWave], realOut[yWave*width+xWave] * realOut[yWave*width+xWave]);
                    }
                }
       //     }
        } return new ComplexImage(amplitudeOut,phaseOut);
    }
}
