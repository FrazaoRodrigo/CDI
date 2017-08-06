package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;

import java.util.Arrays;
import java.util.concurrent.Callable;


import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;

public class CT_Rows_Call implements Callable {

    int startIndx,nbrOfRows,nThreads,width;
    SparseMatrix sparseMatrix;

    public CT_Rows_Call(int startIndx, int nThreads, SparseMatrix sparseMatrix) {
        this.startIndx = startIndx;
        this.nbrOfRows = sparseMatrix.getNumberOfRows();
        this.nThreads = nThreads;
        this.sparseMatrix = sparseMatrix;
        this.width=sparseMatrix.getNumberOfColumns();
    }

    @Override
    public Complex[] call() throws Exception {

      Complex[] rowsFFT = new Complex[width];
            for(int j = startIndx; j< nbrOfRows; j+=nThreads) {
                rowsFFT =fft_ct(sparseMatrix.getRow(j));
            }
    return rowsFFT;
    }
}
