package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.ThreadService.fft_CT_collums;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.fft_CT_rows;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.rowsCT;

public class CT_TwoD_FFT {

    public static void letsee(ComplexImage image, int nThreads) throws ExecutionException, InterruptedException {
        Complex[][] complexArrayImage = image.toComplexArray();
        SparseMatrix imageSparse = new SparseMatrix(complexArrayImage);
        Complex[] horizontalFFT = rowsCT(nThreads,imageSparse);

    }

    public static ComplexImage twoD_fft_ct(ComplexImage image, int nThreads) throws ExecutionException, InterruptedException {
        int height = image.getHeight();
        int width = image.getWidth();

        Complex[][] complexes = image.toComplexArray();
        HashMap<Integer,Complex[]> horizontalFFT = fft_CT_rows(nThreads,complexes);
        HashMap<Integer,Complex[]> verticalFFT = fft_CT_collums(nThreads,buildfromhashMap(horizontalFFT,height,width));

        return buildFromCollums(verticalFFT,height,width);
    }

    private static Complex[][] buildfromhashMap(HashMap<Integer,Complex[]> input,int height,int width){
        Complex[][] complexes = new Complex[height][width];
        for (int i = 0; i<height;i++ ){
            complexes[i]= input.get(i);
        }
        return complexes;
    }

    private static ComplexImage buildFromCollums(HashMap<Integer,Complex[]> complexes,int height, int width){

        double[][] outAmplitude = new double[height][width];
        double[][] outPhase = new double[height][width];

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[j][k] = complexes.get(k)[j].abs();
                outPhase[j][k] = complexes.get(k)[j].phase();
            }
        }
        return new ComplexImage(outAmplitude,outPhase);
    }


}
