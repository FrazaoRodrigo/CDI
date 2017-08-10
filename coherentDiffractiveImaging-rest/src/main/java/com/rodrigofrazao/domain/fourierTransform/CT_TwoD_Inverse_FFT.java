package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.buildFromSparseMatrix;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.collumsCT;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.rowsCT;

public class CT_TwoD_Inverse_FFT {

    public static ComplexImage ifft_ct(ComplexImage image, int nThreads) throws ExecutionException, InterruptedException {

        SparseMatrix imageSparse = new SparseMatrix(image);
        SparseMatrix horizontalFFT = rowsCT(nThreads,imageSparse,"inverse");
        SparseMatrix verticalFFT = collumsCT(nThreads,horizontalFFT,"inverse");

        return buildFromSparseMatrix(verticalFFT);
    }
}
