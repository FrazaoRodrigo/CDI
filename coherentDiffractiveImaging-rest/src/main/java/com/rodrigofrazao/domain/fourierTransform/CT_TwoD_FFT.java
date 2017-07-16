package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.ThreadService.fft_CT_collums;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.fft_CT_rows;

public class CT_TwoD_FFT {

    public static ComplexImage twoD_fft_ct(ComplexImage image, int nThreads) throws ExecutionException, InterruptedException {
        int width = image.getWidth();
        int height = image.getHeight();

        Complex[][] complexes = image.toComplexArray();
        Complex[][] horizontalFFT = fft_CT_rows(nThreads,complexes);
        Complex[][] verticalFFT = fft_CT_collums(nThreads,horizontalFFT,height,width);

        return buildFromCollums(verticalFFT,height,width);
    }

    public static Complex[] getCollums(int index,Complex[][] matrix,int height){
        Complex[] result = new Complex[height];
        for(int i = 0; i < height; i++)
        {
            result[i] = matrix[i][index];
        }
        return result;
    }

    public static Complex[] getRows(int index,Complex[][] matrix,int width){
        Complex[] result = new Complex[width];
        for(int i = 0; i < width; i++)
        {
            result[i] = matrix[index][i];
        }
        return result;
    }

    private static ComplexImage buildFromCollums(Complex[][] complexes,int height, int width){

        double[][] outAmplitude = new double[height][width];
        double[][] outPhase = new double[height][width];

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[j][k] = complexes[k][j].abs();
                outPhase[j][k] = complexes[k][j].phase();
            }
        }
        return new ComplexImage(outAmplitude,outPhase);
    }


}
