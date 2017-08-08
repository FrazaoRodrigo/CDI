package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;

import java.util.concurrent.Callable;


import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;

public class CT_Rows_Call implements Callable {

    int startIndx,nbrOfRows,nThreads;
    SparseMatrix sparseMatrix;

    public CT_Rows_Call(int startIndx, int nThreads, SparseMatrix sparseMatrix) {
        this.startIndx = startIndx;
        this.nbrOfRows = sparseMatrix.getHeight();
        this.nThreads = nThreads;
        this.sparseMatrix = sparseMatrix;

    }

    @Override
    public SparseMatrix call() throws Exception {
        SparseMatrix outRow = new SparseMatrix(sparseMatrix.getHeight(),sparseMatrix.getWidth());
            for(int j = startIndx; j< nbrOfRows; j+=nThreads) {
              outRow.setRow(j,fft_ct(sparseMatrix.getRow(j)));
            }
    return outRow;
    }
}
