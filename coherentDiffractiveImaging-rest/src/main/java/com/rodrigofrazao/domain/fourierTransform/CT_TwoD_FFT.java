package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.ThreadService.*;

public class CT_TwoD_FFT {

    public static ComplexImage fft_twoD_ct(ComplexImage image, int nThreads) throws ExecutionException, InterruptedException {

        SparseMatrix imageSparse = new SparseMatrix(image);
        SparseMatrix horizontalFFT = rowsCT(nThreads,imageSparse);
        SparseMatrix verticalFFT = collumsCT(nThreads,horizontalFFT);

        return buildFromSparseMatrix(verticalFFT);
    }


    private static ComplexImage buildFromSparseMatrix(SparseMatrix matrix){
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        double[][] outAmplitude = new double[height][width];
        double[][] outPhase = new double[height][width];

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[j][k] = matrix.getElement(j,k).abs();
                outPhase[j][k] = matrix.getElement(j,k).phase();
            }
        }

        return new ComplexImage(outAmplitude,outPhase);
    }
    
}
